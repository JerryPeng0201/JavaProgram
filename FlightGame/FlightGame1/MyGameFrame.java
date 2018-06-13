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

public class MyGameFrame extends JFrame{
  //Load the pictures
  Image planeImg = GameUtil.getImage("images/plane1.png");
  Image background = GameUtil.getImage("images/background1.png");

  //Initialize plane, shells, explode and startTime
  PlaneObject plane = new PlaneObject(planeImg,600,600);
  CannonShell[] shells = new CannonShell[20];//设置炮弹参数
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
        g.setColor(Color.RED);
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
  */
  public void launchFrame(){

    JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
    JPanel rightbar = new JPanel();
    rightbar.setLayout(new GridLayout(0,1));
    //JLabel question = new JLabel("How many shells you want to have?");
    //JTextField number = new JTextField("20");
    JButton start = new JButton("Start");
    JButton reStart = new JButton("Re-Start");
    rightbar.add(start);rightbar.add(reStart);
    content.add(rightbar,BorderLayout.LINE_END);
    start.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        //number.setText("");
        new paintThread().start(); //Start the thread of redrawing the pictures
      }
    });

    reStart.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        shells = new CannonShell[20];

        //CannonShell[] shell2 = new CannonShell[20];
        //new paintThread().start();
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
        //Initialize 15 CannonShells
        for(int i = 0; i<shells.length; i++){
          shells[i]=new CannonShell();
        }

      }
    });
    //--------------------------无辜的分界线--------------------------------------
    JFrame window = new JFrame("Controls");
    window.setContentPane(content);
    window.setSize(100,100);
    window.setLocation(1300,50);
    window.setVisible(true);

    this.setTitle("Flight Game Demo");
    this.setSize(Constant.GAME_WIDTH+300,Constant.GAME_HEIGHT);
    this.setLocation(50,50);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //new paintThread().start(); //Start the thread of redrawing the pictures
    this.addKeyListener(new KeyMonitor()); //Start the KeyAdapter

    shells = new CannonShell[20];
    //Initialize 15 CannonShells
    for(int i = 0; i<shells.length; i++){
      shells[i]=new CannonShell();
    }

  }

  public static void main(String[] args) {
    MyGameFrame f = new MyGameFrame();
    f.launchFrame();
   /*  JPanel content = new JPanel();
    content.setLayout(new BorderLayout());
    JPanel rightbar = new JPanel();
    rightbar.setLayout(new GridLayout(0,1));
    //JLabel question = new JLabel("How many shells you want to have?");
    //JTextField number = new JTextField("20");
    JButton start = new JButton("Start");
    rightbar.add(start);
    content.add(rightbar,BorderLayout.LINE_END);
    start.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        //number.setText("");
      }
    }); */
/*
    f.setContentPane(content);
    f.setTitle("Flight Game Demo");
    f.setSize(Constant.GAME_WIDTH+300,Constant.GAME_HEIGHT);
    f.setLocation(50,50);
    f.setVisible(true);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
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
