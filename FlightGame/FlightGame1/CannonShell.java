import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;




public class CannonShell extends GameObject{
  double degree;

  public CannonShell(){
    x = 200; y = 200;
    width = 20; height = 20;
    speed = 4;
    degree = Math.random()*Math.PI*2;
  }

  public void draw(Graphics g){
    Color c = g.getColor();
    g.setColor(Color.YELLOW);
    g.fillOval((int)x,(int)y,width,height);
    //The shells will fly with random angle
    x += speed*Math.cos(degree);
    y += speed*Math.sin(degree);
    //The shells will bounce back when they hit the edges of the window
    if(x<0||x>Constant.GAME_WIDTH-width){
      degree = Math.PI-degree;
    }
    if(y<0||y>Constant.GAME_HEIGHT-height){
      degree = -degree;
    }
    g.setColor(c);
  }
}
