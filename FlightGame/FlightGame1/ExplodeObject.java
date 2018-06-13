import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;



public class ExplodeObject{

  double x,y; //The position of the explode
  /*
  * The array of the images
  * I do not want it to load for many times
  * Therefore, I used static. This array will be Initializefor only 1 time
  */
  static Image[] imgs = new Image[4];
  /*
  * static Initialize
  * Use for loop to load these 4 pictures
  */
  static{
    for(int i = 0; i<4; i++){
      imgs[i] = GameUtil.getImage("images/boom/boom"+i+".png");
      imgs[i].getWidth(null);
    }
  }

  int count;

  public void draw(Graphics g){
    if(count<=3){
      g.drawImage(imgs[count],(int)x,(int)y,null);
      count++;
    }
  }

  public ExplodeObject(double x, double y){
    this.x = x;
    this.y = y;
  }
}
