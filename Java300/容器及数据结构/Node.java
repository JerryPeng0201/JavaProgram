//用来表示节点
public class Node{
  Node previous;//上一个节点
  Object obj;
  Node next;//下一个节点

  public Node(){

  }

  public Node(Node previous, Node obj, Node next){
    super();
    this.previous = previous;
    this.obj = obj;
    this.next = next;
  }

  public void setPrevious(Node previous){
    this.previous = previous;
  }

  public Node getPrevious(){
    return previous;
  }

  public Object getObj(){
    return obj;
  }

  public void setObj(Object obj){
    this.obj=obj;
  }

  public Node getNext(){
    return next;
  }

  public void setNext(Node next){
    this.next = next;
  }
}
