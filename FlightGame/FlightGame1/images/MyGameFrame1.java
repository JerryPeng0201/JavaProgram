import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
* This is the main window of the flight game
*/

public class MyGameFrame1 extends JFrame{
  //Load the pictures
  Image planeImg = GameUtil.getImage("images/plane1.png");
  Image background = GameUtil.getImage("images/background1.png");

  //Initialize plane, shells, explode and startTime
  PlaneObject plane = new PlaneObject(planeImg,600,600);
  CannonShell[] shells = new CannonShell[2];//设置炮弹参数
  ExplodeObject explode;
  Date startTime = new Date();//起始时间
  Date endTime;//结束时间
  int timePeriod; //How long the game last.

  //This method is automatically called
  public void paint(Graphics g){
    Color c = g.getColor();
    g.drawImage(background,0,0,null);//draw the background
    plane.drawSelf(g);//draw the plane
    //draw the 50 CannonShells
    for(int j = 0; j<shells.length; j++){
      shells[j].draw(g);
      //Test whether the shell hit the plane,if the result is true, then it hits the plane
      boolean boom = shells[j].getRect().intersects(plane.getRect());
      if(boom){
        plane.live = false;
        explode = new ExplodeObject(plane.x, plane.y);
        /*
        * We need to calculate how long the game last
        * Java automatically use ms as unite, we need to change it to s
        * We also need to change the double to int
        */
        endTime = new Date();
        timePeriod = (int)((endTime.getTime()-startTime.getTime())/1000);
        explode.draw(g);
      }
      //If the plane died, then only print the time for one time
      if(!plane.live){
        g.setColor(Color.WHITE);
        Font f = new Font("Times",Font.BOLD,50);
        g.setFont(f);
        g.drawString("Your time is "+timePeriod+"s",(int)plane.x,(int)plane.y);
      }
    }
    g.setColor(c);
  }

  //----------------------------------------------------------------------------


  //This class can help us to draw the window repaditly
  class paintThread extends Thread{
    public void run(){
      while(true){
        repaint(); //Re-draw the window
        try{
          //1s=1000ms, the pictures will be re-drawed for 25 times in 1 second
          Thread.sleep(40);
        }catch(InterruptedException e){
          e.printStackTrace();
        }
      }
    }
  }

  //This class can allow user to control the plane via keyboard
  class KeyMonitor extends KeyAdapter{
    public void keyPressed(KeyEvent e){
      plane.addDirection(e);
    }

    public void keyReleased(KeyEvent e){
      plane.removeDirection(e);
    }
  }

//------------------------------------------------------------------------------

  /**
  * Initialize the window
  * Pleas add all GUI in this method
  */
  public void launchFrame(){
/*
    this.setTitle("Flight Game Demo");
    this.setVisible(true);
    this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
    this.setLocation(50,50);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
    new paintThread().start(); //Start the thread of redrawing the pictures
    this.addKeyListener(new KeyMonitor()); //Start the KeyAdapter

    //Initialize 15 CannonShells
    for(int i = 0; i<shells.length; i++){
      shells[i]=new CannonShell();
    }

  }

  public static void main(String[] args) {
    MyGameFrame1 f = new MyGameFrame1();
    f.launchFrame();



    //JFrame window = new JFrame("Flight game");
    f.setVisible(true);
    JPanel content = new JPanel();
    f.setLayout(new BorderLayout());
    f.setSize(Constant.GAME_WIDTH+100,Constant.GAME_HEIGHT);
    f.setLocation(50,50);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  //Double buffering tech to solve CannonShell flashing problem
  private Image offScreenImage = null;
  public void update(Graphics g){
    if(offScreenImage == null){
      offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
    }
    Graphics gOff = offScreenImage.getGraphics();
    paint(gOff);
    g.drawImage(offScreenImage,0,0,null);
  }



}
