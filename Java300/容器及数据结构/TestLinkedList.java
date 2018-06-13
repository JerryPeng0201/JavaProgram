import java.util.*;
/**
 * 自己实现一个LinkList，帮助更好理解其类的底层结构
 * 链表由节点组成，所有不同的值均放置在节点中
 * 对链表进行修改或者索引时 
 */

 public class TestLinkedList{
   private Node first;//第一个节点
   private Node last;//最后一个节点
   private int size;//链表的大小
   private Node temp = null;//当前节点的存储位置

   //在链表中根据内容添加节点
   public void add(Object obj){
     if(first == null){
       Node n = new Node();//如果第一个节点为空，则建一个新的节点
       n.setPrevious(null);
       n.setObj(obj);
       n.setNext(null);
       first = n;
       last = n;
     }else{
       //直接往last节点后增加新的节点
       Node n = new Node();
       n.setPrevious(last);
       n.setObj(obj);
       n.setNext(null);
       last.setNext(n);
       last = n;
     }
     size++;
   }

   public int size(){
     return size;
   }

   //根据索引位置返回对象，通过链表依次往后遍历
   public Object get(int index){
     //index如何越界处理？
     rangeCheck(index);
     //遍历链表寻找索引位置
     temp = node(index);
     if(temp!=null){
       return temp.obj;
     }
     return null;
   }

   //移除某一节点上的存储数值
   public void remove(int index){
     temp = node(index);
     if(temp!=null){
       Node up = temp.previous;
       Node down = temp.next;
       up.next = down;
       down.previous = up;
       size--;
     }
   }

   //在指定位置插入指定内容
   public void add(int index, Object obj){
     temp = node(index);
     Node newNode = new Node();
     newNode.obj = obj;
     if(temp!=null){
       Node up = temp.previous;
       up.next = newNode;
       newNode.previous = up;
       newNode.next = temp;
       temp.previous = newNode;
       size++;
     }
   }

   //将指定位置的存储数据替换成指定内容
   public void set(int index, Object obj){
     temp = node(index);
     if(temp!=null){
       temp.obj = obj;
     }
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

   //节点分装
   public Node node(int index){
     if(first == null){
       //若第一个节点为空，则不返回任何数值
     }else{
       temp = first;
       for(int i = 0; i<index; i++){
         temp = temp.next;
       }
     }
     return temp;
   }


   public static void main(String[] args) {
     TestLinkedList list = new TestLinkedList();
     list.add("aaa");
     list.add("bbb");
     list.add("ccc");
     list.add("ddd");
     list.remove(1);
     list.add(1,"hahaha");
     list.set(3,"lalala");
     System.out.println(list.size());
     System.out.println(list.get(1));
     System.out.println(list.get(3));

   }
 }
