package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends superobject{
    public OBJ_Boots(){
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
