package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_blueheart extends superobject{
    public OBJ_blueheart(){
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/blueheart.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
