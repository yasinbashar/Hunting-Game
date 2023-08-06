package main;

import entity.Ent;
import org.w3c.dom.Entity;

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
    public int checkObject(Ent entity,boolean player){
        int index = 999;
        for(int i=0;i<gp.obj.length;i++){
            if(gp.obj[i] != null){
                entity.solidArea.x =entity.worldX +entity.solidArea.x;
                entity.solidArea.y =entity.worldY +entity.solidArea.y;

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up" :
                            entity.solidArea.y-=entity.speed;
                            if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                                if(gp.obj[i].collision == true){
                                    entity.collisionOn = true;
                                }
                                if(player == true){
                                   index = i;
                                }
                            }
                            break;
                    case "down" :
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left" :
                        entity.solidArea.x-=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right" :
                        entity.solidArea.x  +=entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.SolidAreaDefaultX;
                entity.solidArea.y = entity.SolidAreaDefaultY;
                gp.obj[i].solidArea.x =gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y =gp.obj[i].getSolidAreaDefaultY;
            }
        }
        return index;
    }
    
}
