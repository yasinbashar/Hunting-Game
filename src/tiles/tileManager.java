package tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.controlPanel;

public class tileManager {
     controlPanel gp;
    public tileClass[] tile;
   public int mapTileNum[][];

    public tileManager(controlPanel gp){
        this.gp = gp;
       tile = new tileClass[10];
       mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
       getTileImage();
       loadMap("/maps/world01.txt");
    }

    public void getTileImage(){

        try{
           tile[0] = new tileClass();
           tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

           tile[1] = new tileClass();
           tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
           tile[1].collision = true;

           tile[2] = new tileClass();
           tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
           tile[2].collision = true;

           tile[3] = new tileClass();
           tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

           tile[4] = new tileClass();
           tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
           tile[4].collision = true;

           tile[5] = new tileClass();
           tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor01.png"));
           
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){

        try{
         InputStream is = getClass().getResourceAsStream(filePath);
         BufferedReader br = new BufferedReader(new InputStreamReader(is));

         int col = 0;
         int row = 0;

         while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            String line = br.readLine();

            while(col < gp.maxWorldCol){
                String numbers[] = line.split(" ");
                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }

            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
         }

         br.close();
        }
        catch(Exception e){

        }
    }
    public void draw(Graphics2D g2){
           //g2.drawImage(tile[0].image, 0,0, gp.tileSize, gp.tileSize, null);

           int worldRow = 0, worldCol = 0, x = 0, y = 0;

           while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize< gp.player.worldY + gp.player.screenY ) {

               g2.drawImage(tile[tileNum].image , screenX, screenY, gp.tileSize, gp.tileSize, null);
               }
               worldCol++;
               //x += gp.tileSize;

               if(worldCol == gp.maxWorldCol ){
                worldCol = 0;
               worldRow++;
               // x = 0;
               // y+= gp.tileSize;
               }
           }
    }
}
