package seantcanavan;

public class SeanLinkedListTest {

	public static void main(String args[]) {
		SeanLinkedList<Integer> seanll = new SeanLinkedList<Integer>();
		seanll.addFirst(new Integer(12));
		seanll.addFirst(new Integer(11));
		seanll.addFirst(new Integer(10));
		seanll.addFirst(new Integer(9));
		seanll.addFirst(new Integer(8));
		seanll.addFirst(new Integer(7));
		seanll.addFirst(new Integer(6));
		seanll.addFirst(new Integer(5));
		seanll.addFirst(new Integer(4));
		seanll.addFirst(new Integer(3));
		seanll.addFirst(new Integer(2));
		seanll.addFirst(new Integer(1));

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
