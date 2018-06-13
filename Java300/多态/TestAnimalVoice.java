public class TestAnimalVoice{


  public static void testVoice(AnimalVoice c){
    c.voice();
    if(c instanceof Cat){ // instance means c belongs to Cat
      ((Cat)c.catchMouse());
    }
  }

  public static void main(String[] args) {
    /*
    AnimalVoice类里并不包含catchMouse和security的方法
    故而编译器无法编译这两个methods
    需要进行强制转型
    */
    AnimalVoice a = new Cat();
    AnimalVoice b = new Dog();
    AnimalVoice c = new Pig();

    testVoice(a);
    testVoice(b);
    testVoice(c);

    //强制转型
    Cat a2 = (Cat)a;
    a2.catchMouse();
    Dog b2 = (Dog)b;
    b2.security();

    /*
    注意：强制转型时只允许同类转同类，无法进行异类强制转型，否则运行时系统会
    自动报错

    举例：
    Cat a2 = (Cat)b;
    */

  }
}
