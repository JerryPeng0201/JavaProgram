import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class SolorJFrame extends JFrame{

  private static final long serialVersionUID = 4419278382442876209L;

   protected SolorJFrame(){}
   protected SolorJFrame(String s){
       super(s);
   }

  public void launchJFrame(){
    JPanel content = new JPanel();
    content.setLayout(new BorderLayout());

    JPanel buttons = new JPanel();
    JButton mercuryB = new JButton("Mercury"); JLabel mercuryL = new JLabel("Mercury Speed:"); JTextField mercuryS = new JTextField("");
    JButton venusB = new JButton("Venus"); JLabel venusL = new JLabel("Venus Speed:"); JTextField venusS = new JTextField("");
    JButton earthB = new JButton("Earth"); JLabel earthL = new JLabel("Earth Speed:"); JTextField earthS = new JTextField("");
    JButton moonB = new JButton("Moon"); JLabel moonL = new JLabel("Moon Speed:"); JTextField moonS = new JTextField("");
    JButton marsB = new JButton("Mars"); JLabel marsL = new JLabel("Mars Speed:"); JTextField marsS = new JTextField("");
    JButton jupiterB = new JButton("Jupiter"); JLabel jupiterL = new JLabel("Jupiter Speed:"); JTextField jupiterS = new JTextField("");
    JButton saturnB = new JButton("Saturn"); JLabel saturnL = new JLabel("Saturn Speed:"); JTextField saturnS = new JTextField("");
    JButton uranusB = new JButton("Uranus"); JLabel uranusL = new JLabel("Uranus Speed:"); JTextField uranusS = new JTextField("");
    JButton neptuneB = new JButton("Neptune"); JLabel neptuneL = new JLabel("Neptune Speed:"); JTextField neptuneS = new JTextField("");
    buttons.add(mercuryB); buttons.add(mercuryL); buttons.add(mercuryS);
    buttons.add(venusB); buttons.add(venusL); buttons.add(venusS);
    buttons.add(earthB); buttons.add(earthL); buttons.add(earthS);
    buttons.add(moonB); buttons.add(moonL); buttons.add(moonS);
    buttons.add(marsB); buttons.add(marsL); buttons.add(marsS);
    buttons.add(jupiterB); buttons.add(jupiterL); buttons.add(jupiterS);
    buttons.add(saturnB); buttons.add(saturnL); buttons.add(saturnS);
    buttons.add(uranusB); buttons.add(uranusL); buttons.add(uranusS);
    buttons.add(neptuneB); buttons.add(neptuneL); buttons.add(neptuneS);
    buttons.setLayout(new GridLayout(0,3));
    content.add(buttons, BorderLayout.LINE_END);


    this.setContentPane(content);
    this.pack();
    this.setTitle("Solor System Demo");
    this.setVisible(true);
    this.setSize(1500,1000);
    this.setLocation(50,50);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    new paintThread().start();//Initialize the re-paint thread
  }

  //Double buffering tech to solve the planets flashing problem
  private Image offScreenImage = null;
  public void update(Graphics g){
    if(offScreenImage == null){
      offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
    }
    Graphics gOff = offScreenImage.getGraphics();
    paint(gOff);
    g.drawImage(offScreenImage,0,0,null);
  }

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

}
