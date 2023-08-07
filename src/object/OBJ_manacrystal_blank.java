package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_manacrystal_blank extends superobject{
    public OBJ_manacrystal_blank(){
        name ="Manacrystal Blank";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/manacrystal_blank.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
