public class LinkedListDeque <T>{

  /**<BleepBlorp>*/
  private class IntNode {

    /**
     * 之前那个奇奇怪怪T 是我自己写的？？？
     */
    T item;
    IntNode Next;
    IntNode Pre;

    /***public int item;*/


    private IntNode(T x, IntNode next, IntNode pre) {

      item = x;
      Next = next;
      Pre = pre;
    }

  }

    private IntNode sentinel; /**This is circular sentinel !!*/
    private int size;

  public LinkedListDeque(){
    sentinel = new IntNode(null, null, null);
    sentinel.Pre = sentinel;
    sentinel.Next = sentinel;
    size = 0;
  }

/*** we really need this?

  public LinkedListDeque(T x){
    sentinel = new IntNode(77, null, null);
    sentinel.Next = new IntNode(x, null, sentinel);
    last = sentinel.Next;
    size = 1;
  }

***/

  public void addFirst(T item) {
    sentinel.Next = new IntNode(item, sentinel.Next, sentinel);
    sentinel.Next.Next.Pre = sentinel.Next; /**point */
    size += 1;
  }


  public void addLast(T item){

    sentinel.Pre = new IntNode(item, sentinel, sentinel.Pre); /**the old sentinel pre become the new one's pre*/
    sentinel.Pre.Pre.Next = sentinel.Pre;
    size += 1;


  }

  public boolean isEmpty(){
    if (sentinel.Next == sentinel && sentinel.Pre == sentinel && size == 0){
      return true;
    }else{
      return false;
    }

  }

  public int size(){

    return size;
  }

  public void printDeque(){
    IntNode p = sentinel;
    while(p.Next != sentinel){
      System.out.print(p.Next.item + " ");
      p = p.Next;
    }
    System.out.println();

  }

  public T removeLast(){  /**return removed T*/
    /**the old way for removeLast
    p = first;
    if (p != null) and (p.Next != null) {
      while(p.Next.Next != null){
        p = p.Next
      }
      p.Next = null;
    }
    size --;
    */

    if (sentinel.Pre == sentinel) {
      return null;
    }else{
      T removed = sentinel.Pre.item;
      sentinel.Pre = sentinel.Pre.Pre;
      sentinel.Pre.Next = sentinel;
      size -= 1;
      return removed;
    }




  }

  public T removeFirst(){  /**return removed T*/
    if (sentinel.Pre == sentinel) {
      return null;
    }else{
      T removed = sentinel.Next.item;
      sentinel.Next = sentinel.Next.Next;
      sentinel.Next.Pre = sentinel;
      size -= 1;
      return removed;

    }

  }

  public T get(int index){
    IntNode p = sentinel.Next;
    if (index >= size){
      return null;
    }
    while (index > 0) {
      p = p.Next;
      index -= 1;
    }
    return p.item;

  }

  private T getRecursiveHelper(IntNode currNode, int index){
    if (index == 0){
      return currNode.item;
    }
    return getRecursiveHelper(currNode.Next, index-1);
  }

  public T getRecursive(int index){
    if (index >= size){
      return null;

    }
    return getRecursiveHelper(sentinel.Next, index);


  }
}

