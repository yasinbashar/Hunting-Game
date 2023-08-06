package main;

import javax.swing.JPanel;

import entity.Player;
import object.superobject;
import tiles.tileManager;

import java.awt.*;

public class controlPanel extends JPanel implements Runnable{
    //screen setting
    final int originalTileSize =16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    int FPS = 60;
    tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public superobject obj[] = new superobject[10];

    public  controlPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // drawing screen 60 times per second at this interval
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread!=null){

            // long currentTime = System.nanoTime();
            // long currentTime2 = System.currentTimeMillis();

            //  System.out.println("Current Time"+ currentTime);

            //1. UPDATE: update information such character position
            update();
            //2. DRAW
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // how much time remaining before next draw
                remainingTime = remainingTime/1000000; // covert to millisecond

                if(remainingTime < 0) remainingTime = 0;

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        //Tile
        tileM.draw(g2);
        //object
        for(int i=0;i<obj.length;i++){
            if(obj[i] !=null){
                obj[i].draw(g2,this);
            }
        }

        //player
        player.draw(g2);
        g2.dispose();
    }
}
