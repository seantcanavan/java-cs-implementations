package seantcanavan;

import org.junit.jupiter.api.Test;

public class SeanLinkedListTest {

  @Test
  public void Should_Test() {
    SeanLinkedList<Integer> seanLinkedList = new SeanLinkedList<>();
    seanLinkedList.addFirst(12);
    seanLinkedList.addFirst(11);
    seanLinkedList.addFirst(10);
    seanLinkedList.addFirst(9);
    seanLinkedList.addFirst(8);
    seanLinkedList.addFirst(7);
    seanLinkedList.addFirst(6);
    seanLinkedList.addFirst(5);
    seanLinkedList.addFirst(4);
    seanLinkedList.addFirst(3);
    seanLinkedList.addFirst(2);
    seanLinkedList.addFirst(1);

    System.out.println("get index at 4: " + seanLinkedList.get(4));
    System.out.println("index of 8: " + seanLinkedList.indexOf(8));

    System.out.println("Before reverse");
    seanLinkedList.printSelf();
    System.out.println("Reversing...");
    seanLinkedList.reverse();
    seanLinkedList.printSelf();
    System.out.println("After reverse");
    seanLinkedList.reverse();

    seanLinkedList.addLast(13);
    seanLinkedList.addLast(14);
    seanLinkedList.addLast(15);
    seanLinkedList.addLast(16);
    seanLinkedList.addLast(17);
    seanLinkedList.printSelf();
    seanLinkedList.removeFirst();
    seanLinkedList.printSelf();
    seanLinkedList.removeFirst();
    seanLinkedList.printSelf();
    seanLinkedList.removeFirst();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
    seanLinkedList.removeLast();
    seanLinkedList.printSelf();
  }
}
