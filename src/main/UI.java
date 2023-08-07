package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.OBJ_Key;

public class UI {
    controlPanel gp;
    Font arial_40, arial_80_B;
    Graphics2D g2;
    BufferedImage keyImage;

    public UI(controlPanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80_B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D g2){
       this.g2 = g2;

       g2.setFont(arial_40);
       g2.setColor(Color.white);

       if(gp.gameState == gp.playState){
        
       }
       if(gp.gameState == gp.pauseState){
          drawPauseScreen();
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
