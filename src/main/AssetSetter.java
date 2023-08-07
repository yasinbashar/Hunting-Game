package main;

import object.OBJ_Chest;
import object.OBJ_manacrystal_blank;
import object.OBJ_blueheart;

public class AssetSetter {
    controlPanel gp;
    public AssetSetter(controlPanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_blueheart();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_blueheart();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_blueheart();
        gp.obj[2].worldX = 21 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

        gp.obj[11] = new OBJ_blueheart();
        gp.obj[11].worldX = 22 * gp.tileSize;
        gp.obj[11].worldY = 42 * gp.tileSize;

        gp.obj[12] = new OBJ_blueheart();
        gp.obj[12].worldX = 24 * gp.tileSize;
        gp.obj[12].worldY = 41 * gp.tileSize;

        gp.obj[3] = new OBJ_blueheart();
        gp.obj[3].worldX = 22 * gp.tileSize;
        gp.obj[3].worldY = 37 * gp.tileSize;


        gp.obj[4] = new OBJ_blueheart();
        gp.obj[4].worldX = 38 * gp.tileSize;
        gp.obj[4].worldY = 8 * gp.tileSize;

        gp.obj[5] = new OBJ_blueheart();
        gp.obj[5].worldX = 35 * gp.tileSize;
        gp.obj[5].worldY = 7 * gp.tileSize;

        gp.obj[6] = new OBJ_manacrystal_blank();
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 11 * gp.tileSize;

        gp.obj[7] = new OBJ_manacrystal_blank();
        gp.obj[7].worldX = 8 * gp.tileSize;
        gp.obj[7].worldY = 28 * gp.tileSize;

        gp.obj[8] = new OBJ_manacrystal_blank();
        gp.obj[8].worldX = 12 * gp.tileSize;
        gp.obj[8].worldY = 22 * gp.tileSize;

        gp.obj[9] = new OBJ_Chest();
        gp.obj[9].worldX = 10 * gp.tileSize;
        gp.obj[9].worldY = 7 * gp.tileSize;

        gp.obj[10] = new OBJ_Chest();
        gp.obj[10].worldX = 37 * gp.tileSize;
        gp.obj[10].worldY = 42 * gp.tileSize;


    }

}
