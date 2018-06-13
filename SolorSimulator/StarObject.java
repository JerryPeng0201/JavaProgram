import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class StarObject{

  Image img; //The pictures of planets
  double x,y; //The location of the planets
  String name; //The names of planets
  int width,height; //The size of those pictures;

  //Draw the pictures of those planets
  public void draw(Graphics g){
    Color c = g.getColor();
    g.drawImage(img,(int)x,(int)y,null);
    if(this.name != null){ //Print the name of the planets
      g.setColor(Color.WHITE);
      Font f = new Font("Times",Font.BOLD,10);
      g.setFont(f);
      g.drawString(this.name,(int)x,(int)y);
    }
    g.setColor(c);
  }


/*
* These are some constructors
* including one empty constructor
*/

  //Constructor1
  public StarObject(){
    //This is the empty constructor
  }

  //Constructor2
  public StarObject(Image img){
    this.img = img;
    this.width=img.getWidth(null);
    this.height=img.getHeight(null);
    System.out.println(this.width);
  }

  //Constructor3
  public StarObject(Image img, double x, double y){
    this.img = img;
    this.x = x; this.y = y;
  }

  //Constructor4
  public StarObject(String imgPath, double x, double y){
    this(GameUtil.getImage(imgPath),x,y);
  }

  //Constructor5
  public StarObject(String imgPath, double x, double y, String name){
    this(GameUtil.getImage(imgPath), x, y);
    this.name=name;
  }
}
