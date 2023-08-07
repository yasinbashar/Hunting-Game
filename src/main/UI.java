package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class UI {
    controlPanel gp;
    Font arial_40, arial_80_B;
    Graphics2D g2;
    BufferedImage keyImage;
    public int commandNum = 0;

    public UI(controlPanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80_B = new Font("Arial", Font.BOLD, 80);
      
    }

    public void draw(Graphics2D g2){
       this.g2 = g2;

       g2.setFont(arial_40);
       g2.setColor(Color.white);
       //Title State
       if(gp.gameState == gp.titleState){
          drawTitleScreen();
       }
       if(gp.gameState == gp.playState){
           drawScore();
       }
       if(gp.gameState == gp.pauseState){
          drawPauseScreen();
       }
    }

    public void drawScore(){
       g2.setFont(g2.getFont().deriveFont(Font.ROMAN_BASELINE, 48F));
        g2.setColor(Color.BLACK);
        String text;

        text = "Score : ";
       
        g2.drawString(text + gp.player.hasKey * 5, 24,48);
    }

    public void drawTitleScreen(){


        //Menu
        BufferedImage image;
         try{
            image = ImageIO.read(getClass().getResourceAsStream("/TitleScreen/Title screen.jpg"));
        
             g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight, null);
        }catch (IOException e){
            e.printStackTrace();
        }
       
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.setColor(Color.RED);
        String text;
        int x ;
        int y = gp.screenHeight/2;

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x ,y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x ,y);
         if(commandNum == 1){
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x ,y);
         if(commandNum == 2){
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawPauseScreen(){
       g2.setFont(arial_80_B);
       String text = "PAUSED";
       int x = getXforCenteredText(text);
       int y = gp.screenHeight/2;
       
       g2.drawString(text, x, y);
    }

       public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;

        return x;
       }
    
}
