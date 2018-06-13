import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;



public class PlaneObject extends GameObject{
  boolean left,right,up,down;
  boolean live = true;

  public void drawSelf(Graphics g){
    //If the plane does not crash with the shell, then draw the plane
    if(live){
      //Draw the plane
      g.drawImage(img,(int)x,(int)y,null);
      //What will happen when the shells hit the edges?
      if(left){
        x-=speed;
      }else if(right){
        x+=speed;
      }
      if(up){
        y-=speed;
      }else if(down){
        y+=speed;
      }
    }else{
      //If the plane crashed with shell, then we will not draw the plane
      //Therefore, this else is empty
    }

    if(x<0||x>Constant.GAME_WIDTH-width){
      x+=0;
    }else if(y<0||y>Constant.GAME_HEIGHT-height){
      y+=0;
    }

  }

  public PlaneObject(Image img, double x, double y){
    this.img = img;
    this.x = x;
    this.y = y;
    this.speed = 8;
    this.width = img.getWidth(null);
    this.height = img.getHeight(null);
  }

  //When the user press a specific key, the plane will move by following the order
  public void addDirection(KeyEvent e){
    switch (e.getKeyCode()){
      case KeyEvent.VK_LEFT: left = true; break;
      case KeyEvent.VK_UP: up = true; break;
      case KeyEvent.VK_RIGHT: right = true; break;
      case KeyEvent.VK_DOWN: down = true; break;
    }
  }

  //When the user release the key, the plane will stop the move
  public void removeDirection(KeyEvent e){
    switch (e.getKeyCode()){
      case KeyEvent.VK_LEFT: left = false; break;
      case KeyEvent.VK_UP: up = false; break;
      case KeyEvent.VK_RIGHT: right = false; break;
      case KeyEvent.VK_DOWN: down = false; break;
    }
  }
}
