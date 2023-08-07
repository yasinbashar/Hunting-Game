package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_manacrystal_full extends superobject{
    public OBJ_manacrystal_full(){
        name = "Manacrystal Full";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/manacrystal_full.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
