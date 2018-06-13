import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;


public class GameObject{
  Image img;
  double x,y;
  int speed;
  int width,height;

  public void drawSelf(Graphics g){
    g.drawImage(img,(int)x,(int)y,null);
  }

  //constructor1
  public GameObject(Image img,double x,double y,int speed,int width,int height){
    super();
    this.img = img;
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.width = width;
    this.height = height;
  }

  //constructor2
  public GameObject(Image img, double x, double y){
    super();
    this.img = img;
    this.x = x;
    this.y = y;
  }

  //constructor3
  public GameObject(){

  }

  /**
  *return the rectangle which has the object
  *so that we can test whether the object crash another object
  */
  public Rectangle getRect(){
    return new Rectangle((int)x,(int)y,width,height);
  }
}
