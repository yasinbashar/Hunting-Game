package main;

import entity.Ent;
 
public class collisionChecker {
    
     controlPanel gp;

    public collisionChecker(controlPanel gp){
        this.gp = gp;
    }
    
    public void checkTile(Ent entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityUpWorldY = entity.worldY + entity.solidArea.y;
        int entityDownWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityUpRow =  entityUpWorldY / gp.tileSize;
        int entityDownRow = entityDownWorldY / gp.tileSize;

        int tileNum1 , tileNum2;

        switch(entity.direction){

            case "up":
                entityUpRow = (entityUpWorldY - entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityUpRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityUpRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
                }
                break;
            case "down":
              entityDownRow = (entityDownWorldY + entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityDownRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityDownRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
                }
                break;
            case "left":
             entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityDownRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityUpRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
                }
                break;
            case "right":
             entityRightCol = (entityRightWorldX + entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityDownRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityUpRow];

                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                   entity.collisionOn = true;
                }
                break;
        }
    }
    
}
