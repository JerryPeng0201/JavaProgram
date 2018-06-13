import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class PlanetsObject extends StarObject{

  double longAxis; //Long axis of the oval
  double shortAxis; //Short axis of the oval
  double speed; //Speed of those planets
  double degree; //Angles of flying
  StarObject center; //Center of the solar system
  boolean satellite; //Test whether the planet has a satellite

  //The constructor with speed
  public PlanetsObject(StarObject center, String imgPath, double longAxis, double shortAxis, double speed){
    super(GameUtil.getImage(imgPath));
    this.center = center;
    this.x = center.x+longAxis;
    this.y = center.y;
    this.longAxis = longAxis;
    this.shortAxis = shortAxis;
    this.speed = speed;
  }

  //The constructor with the name of the planets
  public PlanetsObject(StarObject center, String imgPath, double longAxis, double shortAxis, double speed, String name){
    this(center, imgPath, longAxis, shortAxis, speed);
    this.name = name;
  }

  //The constructor with satellite
  public PlanetsObject(StarObject center, String imgPath, double longAxis, double shortAxis, double speed, boolean satellite) {
    this(center, imgPath, longAxis, shortAxis, speed);
    this.satellite = satellite;
    }

  public PlanetsObject(StarObject center, String imgPath, double longAxis, double shortAxis, double speed, String name, boolean satellite) {
    this(center, imgPath, longAxis, shortAxis, speed, name);
    this.satellite = satellite;
  }

  //The constructor with the location of the planets
  public PlanetsObject(Image img, double x, double y) {
    super(img, x, y);
  }

  //The constructor with the name
  public PlanetsObject(String imgpath, double x, double y, String name) {
    super(imgpath, x, y, name);
  }

  //Override the draw method if the planet has satellite
  public void draw(Graphics g) {
    super.draw(g);
    if (!satellite) {
      drawTrace(g);
    }
    updatePlanets();
  }

  //Draw the pathways of those planet
  public void drawTrace(Graphics g){
    double ovalX, ovalY;
    double ovalWidth, ovalHeight;
    //Set the parameter of the traces of the planets
    ovalWidth = longAxis*2;
    ovalHeight = shortAxis*2;
    ovalX = (center.x + center.width / 2) - longAxis;
    ovalY = (center.y + center.height / 2) - shortAxis;
    //Draw thses traces;
    Color c = g.getColor();
    //g.setColor(Color.RED);
    g.drawOval((int)ovalX,(int)ovalY,(int)ovalWidth,(int)ovalHeight);
    g.setColor(c);
  }

  //The planets will fly by following the traces
  public void updatePlanets(){
    x = (center.x + center.width / 2) + longAxis * Math.cos(degree) - this.width / 2;
    y = (center.y + center.height / 2) + shortAxis * Math.sin(degree) - this.height / 2;
    degree += speed;
  }
}
