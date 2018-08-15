package seantcanavan;

import org.fest.util.Lists;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeanLinkedListTest {

  @Test
  public void Add_WithMultipleValues_ShouldAddToTheEnd() {
    SeanLinkedList<String> sll = new SeanLinkedList<>();
    sll.add("four");
    assertThat(sll.toList()).isEqualTo(Lists.newArrayList("four"));
    assertThat(sll.getSize()).isEqualTo(1);

    sll.addFirst("five");
    assertThat(sll.toList()).isEqualTo(Lists.newArrayList("five", "four"));
    assertThat(sll.getSize()).isEqualTo(2);

    sll.addLast("three");
    assertThat(sll.toList()).isEqualTo(Lists.newArrayList("five", "four", "three"));
    assertThat(sll.getSize()).isEqualTo(3);

    assertThat(sll.isValid()).isTrue();
  }

  @Test
  public void Clear_AfterAddingAndRemovingValues_ShouldClearAndResetLinkedList() {
    SeanLinkedList<Integer> sll = new SeanLinkedList<>();
    List<Integer> expected = new ArrayList<>();

    int insertCount = 31;
    for (int i = 0; i < insertCount; i++) {
      sll.add(i);
      expected.add(i);
    }

    for (int i = 0; i < insertCount; i++) {
      assertThat(sll.get(i)).isEqualTo(expected.get(i));
      assertThat(sll.indexOf(i)).isEqualTo(i);
    }

    assertThat(sll.getFirst()).isEqualTo(expected.get(0));
    assertThat(sll.getLast()).isEqualTo(expected.get(expected.size() - 1));
    assertThat(sll.isValid()).isTrue();

    sll.clear();

    assertThrows(NoSuchElementException.class, sll::getFirst);
    assertThrows(NoSuchElementException.class, sll::getLast);
    assertThat(sll.isEmpty()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);
    assertThat(sll.isValid()).isTrue();
  }

  @Test
  public void Contains_WithMultipleValuesInserted_ShouldReturnTrueForExistingValues() {
    SeanLinkedList<String> sll = new SeanLinkedList<>();
    sll.addLast("new string");
    sll.addLast("old string");
    sll.add("other string");
    assertThat(sll.contains("new string")).isTrue();
    assertThat(sll.contains("false string")).isFalse();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.toList()).isEqualTo(Lists.newArrayList("new string", "old string", "other string"));
  }

  @Test
  public void PrintSelf_WithRandomlyBuiltValues_ShouldPrintSelfCorrectlyAndCreateValidList() {
    SeanLinkedList<Integer> sll = new SeanLinkedList<>();

    for (int i = 0; i < 10000; i++) {
      sll.add(new SecureRandom().nextInt(100000));
    }

    sll.printSelf();
    assertThat(sll.isValid()).isTrue();
  }

  @Test
  public void Remove_WithMultipleEdgeCases_ShouldMaintainValidList() {
    SeanLinkedList<Integer> sll = new SeanLinkedList<>();
    sll.add(5);
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(1);
    boolean removeResult = sll.remove(5);
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);
    assertThat(removeResult).isTrue();

    sll.add(5);
    sll.add(4);
    assertThat(sll.remove(4)).isTrue();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(1);
    assertThat(sll.getFirst()).isEqualTo(5);
    assertThat(sll.getLast()).isEqualTo(5);

    sll.clear();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);

    sll.add(5);
    sll.add(4);
    assertThat(sll.remove(5)).isTrue();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(1);
    assertThat(sll.getFirst()).isEqualTo(4);
    assertThat(sll.getLast()).isEqualTo(4);

    sll.clear();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);
    sll.add(2);
    sll.add(4);
    sll.add(6);

    assertThat(sll.remove(4)).isTrue();
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getFirst()).isEqualTo(2);
    assertThat(sll.getLast()).isEqualTo(6);
    assertThat(sll.getSize()).isEqualTo(2);
    assertThat(sll.remove(4)).isFalse();

    sll.printSelf();
  }

  @Test
  public void RemoveFirst_WithStandardListSetup_ShouldRemoveAndRemainValid() {
    SeanLinkedList<String> sll = new SeanLinkedList<>();
    sll.add("first string");
    sll.add("second string");
    sll.add("third string");
    sll.add("fourth string");
    sll.add("fifth string");
    sll.add("sixth string");
    sll.add("seventh string");
    sll.add("eighth string");
    sll.add("ninth string");
    sll.add("tenth string");

    assertThat(sll.getSize()).isEqualTo(10);

    String data = sll.removeFirst();
    assertThat(data).isEqualTo("first string");
    assertThat(sll.getSize()).isEqualTo(9);
    assertThat(sll.getFirst()).isEqualTo("second string");
    assertThat(sll.getLast()).isEqualTo("tenth string");
    sll.printSelf();
    assertThat(sll.isValid()).isTrue();

    data = sll.removeLast();
    assertThat(data).isEqualTo("tenth string");
    assertThat(sll.getSize()).isEqualTo(8);
    assertThat(sll.getFirst()).isEqualTo("second string");
    assertThat(sll.getLast()).isEqualTo("ninth string");
    assertThat(sll.isValid()).isTrue();

    data = sll.removeLast();
    assertThat(data).isEqualTo("ninth string");
    assertThat(sll.getSize()).isEqualTo(7);
    assertThat(sll.getFirst()).isEqualTo("second string");
    assertThat(sll.getLast()).isEqualTo("eighth string");
    assertThat(sll.isValid()).isTrue();

    data = sll.removeLast();
    assertThat(data).isEqualTo("eighth string");
    assertThat(sll.getSize()).isEqualTo(6);
    assertThat(sll.getFirst()).isEqualTo("second string");
    assertThat(sll.getLast()).isEqualTo("seventh string");
    assertThat(sll.isValid()).isTrue();

    data = sll.removeFirst();
    assertThat(data).isEqualTo("second string");
    assertThat(sll.getSize()).isEqualTo(5);
    assertThat(sll.getFirst()).isEqualTo("third string");
    assertThat(sll.getLast()).isEqualTo("seventh string");
    assertThat(sll.isValid()).isTrue();
  }

  @Test
  public void RemoveFirst_WithEdgeCases_ShouldResetListProperly() {
    SeanLinkedList<Integer> sll = new SeanLinkedList<>();
    sll.add(5);
    Integer data = sll.removeLast();
    assertThat(data).isEqualTo(5);
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);
    sll.add(5);
    data = sll.removeFirst();
    assertThat(data).isEqualTo(5);
    assertThat(sll.isValid()).isTrue();
    assertThat(sll.getSize()).isEqualTo(0);

    sll.add(4);
    sll.add(5);
    data = sll.removeLast();
    assertThat(data).isEqualTo(5);
    assertThat(sll.getSize()).isEqualTo(1);
    assertThat(sll.isValid()).isTrue();
    data = sll.removeFirst();
    assertThat(data).isEqualTo(4);
    assertThat(sll.getSize()).isEqualTo(0);
    assertThat(sll.isValid());

    sll.clear();
    assertThat(sll.isValid());

    sll.add(4);
    sll.add(5);
    data = sll.removeFirst();
    assertThat(data).isEqualTo(4);
    assertThat(sll.getSize()).isEqualTo(1);
    assertThat(sll.isValid()).isTrue();
    data = sll.removeLast();
    assertThat(data).isEqualTo(5);
    assertThat(sll.getSize()).isEqualTo(0);
    assertThat(sll.isValid()).isTrue();
  }

  @Test
  public void Reverse_WithManyValuesAddedMultipleTimes_ShouldReverseStringProperly() {
    int elementCount = 71;
    SeanLinkedList<Integer> sll = new SeanLinkedList<>();

    List<Integer> expectedForward = Lists.newArrayList();
    List<Integer> expectedReverse = Lists.newArrayList();

    for (int i = 0; i < elementCount; i++) {
      sll.add(i);
      expectedForward.add(i);
      expectedReverse.add(i);
    }

    Collections.reverse(expectedReverse);

    assertThat(sll.toList()).isEqualTo(expectedForward);
    sll.reverse();
    assertThat(sll.toList()).isEqualTo(expectedReverse);
    assertThat(sll.isValid());
    sll.reverse();
    assertThat(sll.toList()).isEqualTo(expectedForward);
    assertThat(sll.isValid());
  }
}
