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
    public collisionChecker cChecker;

    int FPS = 60;
    tileManager tileM = new tileManager(this);
    public keyHandler keyH = new keyHandler(this);
    Sound sound = new Sound();
    public UI ui = new UI(this);
    Thread gameThread;

    public collisionChecker colCheck = new collisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public superobject obj[] = new superobject[20];
    
    // Game State 
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    public controlPanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();

        playMusic(0);
        gameState = titleState;
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
        if(gameState == playState){
        player.update();
        }
        if(gameState == pauseState){
            //nothing
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Title Screen

        if( gameState == titleState){
           ui.draw(g2);
        }
        else{
       //tiles
        tileM.draw(g2);
        //object
        for(int i=0;i<obj.length;i++){
            if(obj[i] !=null){
                obj[i].draw(g2,this);
            }
    
        }
        
        
        //player
        player.draw(g2);
        
        ui.draw(g2);

        }
       
        g2.dispose();
    }
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();

    }
}
