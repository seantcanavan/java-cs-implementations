package seantcanavan;


import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.fest.assertions.api.Assertions.assertThat;

public class BinarySearchTreeTest {

    private final String subset = "1234567890";
    private final int length = 5;
    private final Random random = new Random();

    @Test
    public void shouldAddManyValuesToTheTree() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        for (int i = 0; i < 100000; i++) {
            bst.add(randomStringGenerator(subset, length));
        }

        assertThat(bst.validTree()).isTrue();
    }

    @Test
    public void shouldAddSameValueManyTimesCorrectly() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();

        for (int i = 0; i < 5; i++) {
            bst.add("s");
        }

        assertThat(bst.validTree()).isTrue();
        assertThat(bst.find("s").getLeft()).isNull();
        assertThat(bst.find("s").getRight()).isNull();
    }

    @Test()
    public void shouldDeleteCorrectly() {
        BinarySearchTree<String> bst = new BinarySearchTree<>();
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
        BinarySearchTree<String> bst = new BinarySearchTree<>();
        LinkedList<String> insertedValues = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            String random = randomStringGenerator(subset, length);
            insertedValues.add(random);
            bst.add(random);
        }

        for (int i = 0; i < 100; i++) {
            if (randomOperation() == 0) {
                String next = randomStringGenerator(subset, length);
                insertedValues.add(next);
                bst.add(next);
            } else {
                String toDelete = insertedValues.pop();
                bst.delete(toDelete);
            }
        }

        assertThat(bst.validTree()).isTrue();
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
