package seantcanavan;

public class SeanBinaryTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeanBinaryTree<Integer> sbt = new SeanBinaryTree<Integer>();
		sbt.add(50);
		sbt.add(25);
		sbt.add(75);
		sbt.add(12);
		sbt.add(37);
		sbt.add(62);
		sbt.add(87);
		sbt.add(6);
		sbt.add(18);
		sbt.add(31);
		sbt.add(43);
		sbt.add(56);
		sbt.add(68);
		sbt.add(81);
		sbt.add(93);
		System.out.println(sbt.contains(20));
		System.out.println(sbt.contains(87));

		sbt.printSelfBreadthFirst();
		sbt.delete(50);
		sbt.printSelfBreadthFirst();
		sbt.delete(31);
		sbt.printSelfBreadthFirst();
		sbt.add(31);
		sbt.add(50);
		sbt.printSelfDepthFirstInOrder();
	}
}
