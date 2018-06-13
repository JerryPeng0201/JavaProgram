public class AnimalVoice{

  String str;

  public void voice(){
    System.out.println("Regular Animal Voice");
  }

}

class Cat extends AnimalVoice{
  public void voice(){
    System.out.println("mmm");
  }

  public void catchMouse(){
    System.out.println("catch those mouse!");
  }
}

class Dog extends AnimalVoice{
  public void voice(){
    System.out.println("www");
  }

  public void security(){
    System.out.println("Secure the door!");
  }
}

class Pig extends AnimalVoice{
  public void voice(){
    System.out.println("hhh");
  }
}
