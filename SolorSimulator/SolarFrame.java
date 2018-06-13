import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/*
* This is a simulator of solar system. This is the main window.
* This program can import 9 planets in the solar system and simulate their
* pathways around the sun. At the beginning the user need to input which
* planet(s) they want to see, and click "Go" button, then the simulator will
* show them on the screen. The user can use their mouse to control a small
* flight. If the plane crashes the planet, it will explode
*/

public class SolarFrame extends SolorJFrame{


  private static final long serialVersionUID = 1219934545878639839L;

   protected SolarFrame(String s){
       super(s);
   }

   //Load the background pictures
   Image background = GameUtil.getImage("images1/background1.png");

   //Load the sun picture
   StarObject sun = new StarObject("images1/sun.png", Constant.GAME_WIDTH/2, Constant.GAME_HEIGHT/2, "Sun");

   //Load the planets pictures
   PlanetsObject mercury = new PlanetsObject(sun, "images1/mercury.png",50,36,0.4446,"Mercury");
   PlanetsObject venus = new PlanetsObject(sun, "images1/venus.png",88,67,0.0176,"Venus");
   PlanetsObject earth = new PlanetsObject(sun, "images1/earth.png",150,93,.0108,"Earth");
   PlanetsObject moon = new PlanetsObject(earth, "images1/moon.png",15,10,0.1332,"Moon",true);
   PlanetsObject mars = new PlanetsObject(sun, "images1/mars.png",210,120,0.0056,"Mars");
   PlanetsObject jupiter = new PlanetsObject(sun, "images1/jupiter.png",240,150,0.0008,"Jupiter");
   PlanetsObject saturn = new PlanetsObject(sun, "images1/saturn.png",270,180,0.0004,"Saturn");
   PlanetsObject uranus = new PlanetsObject(sun, "images1/uranus.png",300,210,0.00008,"Uranus");
   PlanetsObject neptune = new PlanetsObject(sun, "images1/neptune.png",350,250,0.00001,"Neptune");

   //Override the draw method
   public void paint(Graphics g){
    g.drawImage(background, 0, 0, null);
    sun.draw(g);
    mercury.draw(g);
    venus.draw(g);
    earth.draw(g);
    moon.draw(g);
    mars.draw(g);
    jupiter.draw(g);
    saturn.draw(g);
    uranus.draw(g);
    neptune.draw(g);
   }

   public static void main(String[] args) {
      new SolarFrame("Solar System simulator").launchJFrame();
    }

}
