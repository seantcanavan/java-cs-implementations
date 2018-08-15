package seantcanavan;

import org.junit.jupiter.api.Test;
import seantcanavan.components.DoubleLinkNode;

import java.util.LinkedList;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class SeanBinaryTreeTest {

  private final String subset = "1234567890";
  private final int length = 5;
  private final Random random = new Random();

  @Test
  public void shouldAddManyValuesToTheTree() {
    SeanBinaryTree<String> bst = new SeanBinaryTree<>();

    for (int i = 0; i < 100000; i++) {
      bst.add(randomStringGenerator(subset, length));
    }

    assertThat(bst.validTree()).isTrue();
  }

  @Test
  public void shouldAddSameValueManyTimesCorrectly() {
    SeanBinaryTree<String> bst = new SeanBinaryTree<>();

    for (int i = 0; i < 5; i++) {
      bst.add("s");
    }

    assertThat(bst.validTree()).isTrue();
    assertThat(bst.find("s").getLeft()).isNull();
    assertThat(bst.find("s").getRight()).isNull();
  }

  @Test()
  public void shouldDeleteCorrectly() {
    SeanBinaryTree<String> bst = new SeanBinaryTree<>();
    LinkedList<String> inserted = new LinkedList<>();
    int elements = 8;

    for (int i = 0; i < elements; i++) {
      String random = randomStringGenerator("12345", 3);
      bst.add(random);
      inserted.add(random);
    }

    bst.printBreadthFirst();

    assertThat(bst.validTree()).isTrue();

    for (int x = 0; x < elements - 3; x++) {
      bst.delete(inserted.get(x));
    }

    bst.printBreadthFirst();
    assertThat(bst.validTree()).isTrue();
  }

  @Test
  public void shouldPerformManyRandomActions() {
    SeanBinaryTree<String> bst = new SeanBinaryTree<>();
    LinkedList<String> insertedValues = new LinkedList<>();

    for (int i = 0; i < 10000; i++) {
      String random = randomStringGenerator(subset, length);
      insertedValues.add(random);
      bst.add(random);
    }

    for (int i = 0; i < 10000; i++) {
      if (randomOperation() == 0) {
        String next = randomStringGenerator(subset, length);
        insertedValues.add(next);
        bst.add(next);
      } else {
        String toDelete = insertedValues.pop();
        bst.delete(toDelete);
      }
    }

    System.out.println(bst.getSize());
    assertThat(bst.validTree()).isTrue();
  }

  @Test
  public void ValidTree_WithMultipleInvalidTrees_ShouldReturnAllInvalid() {
    DoubleLinkNode<Integer> root = new DoubleLinkNode<>(50);

    DoubleLinkNode<Integer> tier2_left = new DoubleLinkNode<>(25);
    DoubleLinkNode<Integer> tier2_right = new DoubleLinkNode<>(75);

    root.setLeft(tier2_left);
    root.setRight(tier2_right);

    DoubleLinkNode<Integer> tier3_1_left = new DoubleLinkNode<>(10);
    DoubleLinkNode<Integer> tier3_1_right = new DoubleLinkNode<>(28);

    DoubleLinkNode<Integer> tier3_2_left = new DoubleLinkNode<>(30);
    DoubleLinkNode<Integer> tier3_2_right = new DoubleLinkNode<>(62);

    tier2_left.setLeft(tier3_1_left);
    tier2_left.setRight(tier3_1_right);

    tier2_right.setLeft(tier3_2_left);
    tier2_right.setRight(tier3_2_right);

    SeanBinaryTree<Integer> sbst = new SeanBinaryTree<>();
    assertThat(sbst.validTree(root)).isFalse();
  }

  private int randomOperation() {
    return random.nextInt(2);
  }

  private String randomStringGenerator(String subset, int length) {
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length) { // length of the random string.
      int index = (int) (random.nextFloat() * subset.length());
      sb.append(subset.charAt(index));
    }
    return sb.toString();
  }
}
