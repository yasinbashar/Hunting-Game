package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import main.controlPanel;
import main.keyHandler;

public class Player extends Ent {
    controlPanel gp;
    keyHandler keyH;

    public final int screenX, screenY;
    public int hasKey =0;

    public Player(controlPanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        screenY = gp.screenHeight/2 - gp.tileSize/2;
        screenX = gp.screenWidth/2 - gp.tileSize/2;

        solidArea = new Rectangle( 8, 16, 32, 32);
        SolidAreaDefaultX = 8;
        SolidAreaDefaultY = 16;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
         spriteCounter = 0;
         spriteNum = 1;
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/playerSprite/oldman_left_2.png"));
        }
        catch(IOException e){
            e.printStackTrace();

        }
    }

    public void update(){

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true)
        {
            if(keyH.upPressed == true){
                direction = "up";
             
            }
            else if(keyH.downPressed == true){
                direction = "down";
               
            }
            else if(keyH.rightPressed == true){
                direction = "right";
               
            }
            else if(keyH.leftPressed == true){
                direction = "left";
              
            }
            // check tile collision
            collisionOn = false;
            gp.colCheck.checkTile(this);
            //CHECK OBJECT COLLISION
            int objIndex = gp.colCheck.checkObject(this,true);
            pickUpObject(objIndex);

            // if collision is false, player can move
            if(collisionOn == false){
                switch(direction){
                    case "up":
                    worldY -= speed;
                    break;
                    case "down":
                     worldY += speed;
                    break;
                    case "left":
                    worldX -= speed;
                    break;
                    case "right":
                    worldX += speed;
                    break;
                }
            }

            spriteCounter++;
            if(spriteCounter> 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else{
                    spriteNum = 1;
                }

                spriteCounter = 0;
            }
        }
    }
    public void pickUpObject(int i){
        if(i!=999){
           String objectName = gp.obj[i].name;
           switch (objectName){
               case "Key":
                   gp.playSE(1);
                   hasKey++;
                   gp.obj[i] = null;
                   break;
               case "Manacrystal Blank":

                       gp.playSE(3);
                       gp.obj[i]=null;
                       hasKey++;


                   break;
               case "Manacrystal Full":
                   gp.playSE(2);
                   gp.obj[i] = null;
                   hasKey++;
                   break;
           }
        }
    }


    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        //g2.fillRect( x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "right":
                if(spriteNum == 1)
                    image = right1;
                else
                    image = right2;
                break;
            case "left":
                if(spriteNum == 1)
                    image = left1;
                else
                    image = left2;
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
