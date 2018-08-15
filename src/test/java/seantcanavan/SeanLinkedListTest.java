package seantcanavan;

import org.junit.jupiter.api.Test;

public class SeanLinkedListTest {

  @Test
  public void Should_Test() {
    SeanLinkedList<Integer> seanll = new SeanLinkedList<>();
    seanll.addFirst(12);
    seanll.addFirst(11);
    seanll.addFirst(10);
    seanll.addFirst(9);
    seanll.addFirst(8);
    seanll.addFirst(7);
    seanll.addFirst(6);
    seanll.addFirst(5);
    seanll.addFirst(4);
    seanll.addFirst(3);
    seanll.addFirst(2);
    seanll.addFirst(1);

    System.out.println("get index at 4: " + seanll.get(4));
    System.out.println("index of 8: " + seanll.indexOf(8));

    System.out.println("Before reverse");
    seanll.printSelf();
    System.out.println("Reversing...");
    seanll.reverse();
    seanll.printSelf();
    System.out.println("After reverse");
    seanll.reverse();

    seanll.addLast(13);
    seanll.addLast(14);
    seanll.addLast(15);
    seanll.addLast(16);
    seanll.addLast(17);
    seanll.printSelf();
    seanll.removeFirst();
    seanll.printSelf();
    seanll.removeFirst();
    seanll.printSelf();
    seanll.removeFirst();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
    seanll.removeLast();
    seanll.printSelf();
  }
}
