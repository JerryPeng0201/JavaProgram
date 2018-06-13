/*
This program is kind of like an app. It can change the user's keyboard to instrument.
We will use methods, arrays, databases, loops, conditions, and TextIO to finish this
program. We will make one array to store the keyboard buttons and one database to store
music files. Then we will deploy some Java built-in packages to connect keyboard buttons
and music files. Our first database will be piano music files. If we have enough time, we
will build more databases to store other genres or types music files so that the user
can chose their favorite instrument music special effects.

Compilation: javac MyMagicKeyboard.java
Execution: java MyMagicKeyboard, and push the button of the keyboard
Required Packages: StdAudio.java

Assistance: http://book.51cto.com/art/201612/526122.htm
*/

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;


/**
 *
 */

/**
 * @author Administrator
 *
 */

//Build a frame
public class MyMagicKeyboard extends JFrame{

  /**
   * @param args
   */

  char charA;

  public static void main(String[] args){
    // TODO Auto-generated method stub
    new MyMagicKeyboard();
    printOut();
  }

  public static void printOut(){
    System.out.println("Welcome to MagicKeyboard app!");
    System.out.println("This app can entrust your keyboard background music!");
  }

  public MyMagicKeyboard(){
    this.setSize(500,100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("My MagicKeyboard");
    this.setVisible(true);
    this.addKeyListener(new MyKeyListener());
  }
}
//monitor keyboard
class MyKeyListener extends KeyAdapter{

  public static char[] keyboard = {
    'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
    'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
    'z', 'x', 'c', 'v', 'b', 'n', 'm'
  };//Declare the keyboard buttions have BGM
  public static int sps = 44100;
  public static double hz = 440.00; //Base frequency
  public static double duration = 0.3; //How long will sound be played
  public static int n = (int)(sps * duration);
  public static double[] a = new double[n + 1];
  public static double fre = 0.0;//the frequency of the sound


  public void keyPressed(KeyEvent e){

    char charA = e.getKeyChar();

    double hezi = frequency(charA, 0.0);

    a = soundFrequency(hezi);

    StdAudio.play(a);
  }


  public static double frequency(char charA, double fre){
    //Calculate each button's frequency
    for(int j = 0; j < keyboard.length; j++){
      if(charA == keyboard[j]){ //Detec which button did the user push
        fre = Math.pow(2, (j-24)/12.0) * hz; //Calculate each button's frequency
      }
    }
    return fre; // return the frequency of the button pushed by the user
  }


  public static double[] soundFrequency(double hezi){
    //Select from wave form
    for(int i = 0; i < (n + 1); i ++){
      //Calculate and select the sound from the wave form.
      a[i] = Math.sin(2.0 * Math.PI * i * hezi / sps);// Select from sound wave
    }
    return a;//return the selected sound
  }

}
