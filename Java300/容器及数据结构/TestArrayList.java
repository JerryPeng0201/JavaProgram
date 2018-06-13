import java.util.*;
/**
 * 自己实现一个ArrayList，帮助更好理解其类的底层结构
 *
 */

public class TestArrayList{

  private Object[] elementData;
  private int size;

  //获取数组长度
  public int size(){
    return size;
  }

  //判断数组是否为空
  public boolean isEmpty(){
    return size == 0;//当size为0的时候，数组为空
  }

  //获取数组中一个位置的值
  public Object get(int index){
    rangeCheck(index);
    return elementData[index];
  }

  //空构造器
  public TestArrayList(){
    //Empaty constructor
    this(10);//默认数组长度为10
  }

  //含参构造器
  public TestArrayList(int initialCapicity){
    if(initialCapicity<0){
      try{
        throw new Exception();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
    elementData = new Object[initialCapicity]; //新建一个数组
  }

  //删除指定位置的对象
  public void remove(int index){
    rangeCheck(index);
    int numMoved = size - index - 1;
    if(numMoved > 0){
      System.arraycopy(elementData,index+1,elementData,index,numMoved);
    }
    elementData[--size]=null;
  }

  //删除包含指定内容的所有对象
  public void remove(Object obj){
    for(int i = 0; i<size; i++){
      if(get(i).equals(obj)){ //注意：底层调用的方法是equals而不是==
        remove(i);
      }
    }
  }

  //在指定位置插入元素
  public void add(int index, Object obj){
    ensureCapacity(size);
    rangeCheck(index);
    System.arraycopy(elementData,index,elementData,index+1,size-index);
    elementData[index] = obj;
    size++;
  }

  //在数组中加入元素
  public void add(Object obj){
    ensureCapacity(size);
    elementData[size] = obj;//size初始数据为零，故obj添加位置为0
    size++;//每添加一次obj，ArrayList自动加一位容器总量
  }

  //在某索引位置替换
  public Object set(int index, Object obj){
    rangeCheck(index);
    Object oldValue = elementData[index];
    elementData[index] = obj;
    return oldValue;
  }

  //异常状态监测
  private void rangeCheck(int index){
    if(index<0||index>size){
      try{
        throw new Exception();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  //数据扩容
  private void ensureCapacity(int size){
    /**
     * 数组扩容
     * 表象为每当元素位数超过当前数组容量时,数组的容量自动加一
     * 实际为每当元素位数超过当前数组容量时，推倒当前数组并重新建一个新数组
     * 举个例子，当一个筐子装不下所有苹果时，表象是把筐子拉大使其能装更多的苹果
     * 实际为换了一个更大的筐子所以能装更多的苹果
     */
    if(size+1>elementData.length){
      Object[] newArray = new Object[size*2+1];//重新做一个更大的筐子
      //把之前筐子里的苹果转移到新的筐子里
      System.arraycopy(elementData/*原数组*/,
      0/*从原数组第几位开始拷贝，此处为拷贝全部数组，故为0*/,
      newArray/*将原数据放到新数组里*/,
      0/*从新数组第几位开始打印，由于此处为重新新建数组并存储所有原数据，故为0*/,
      elementData.length/*原数组的长度*/);
      //将之前的老篮子替换成新篮子
      elementData = newArray;
    }
  }

  //程序主方法
  public static void main(String[] args) {
    TestArrayList list = new TestArrayList(3);
    list.add("Sylvia I love you so much!");
    list.add("Please stay here with me during the summer!");
    list.add("I want to live with you!");
    list.add("We'll go to China in 3 weeks!");
    System.out.println("size = "+list.size);
    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    System.out.println(list.get(3));
  }
}
