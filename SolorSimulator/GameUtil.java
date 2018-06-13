import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil{
  private GameUtil(){

  }

  /**
  * Return images which are stored in specific location
  */

  public static Image getImage(String path){
    BufferedImage image = null;
    try{
      URL u = GameUtil.class.getClassLoader().getResource(path);
      image = ImageIO.read(u);
    }catch(IOException e){
      e.printStackTrace();
    }
    return image;
  }
}
