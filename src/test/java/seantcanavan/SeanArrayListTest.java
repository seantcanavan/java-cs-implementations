package seantcanavan;

import org.fest.util.Lists;
import org.junit.jupiter.api.Test;
import seantcanavan.components.DataElement;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;
import static seantcanavan.SeanArrayList.DEFAULT_CAPACITY;

public class SeanArrayListTest {

  @Test
  public void Constructor_WithAllConstructorArguments_ShouldCorrectlyInitializeSeanArrayList() {
    // empty constructor
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    assertThat(sal.getAllocatedCapacity()).isEqualTo(DEFAULT_CAPACITY);

    // initial value constructor
    int initialCapacity = 20;
    sal = new SeanArrayList<>(initialCapacity);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(initialCapacity);

    // initialize with another list
    int initialElementCount = 27;
    int reallocationCount = 2;
    List<DataElement> initialElements = new ArrayList<>();
    for (int i = 0; i < initialElementCount; i++) {
      initialElements.add(DataElement.getRandom());
    }

    sal = new SeanArrayList<>(initialElements);
    assertThat(sal.getSize()).isEqualTo(initialElementCount);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(DEFAULT_CAPACITY * 2 * reallocationCount);
    assertThat(sal.getAllocatedCapacity()).isGreaterThan(sal.getSize());

    for (int i = 0; i < initialElementCount; i++) {
      assertThat(initialElements.get(i)).isEqualTo(sal.get(i));
    }
  }

  @Test
  public void Add_WithMultipleValuesInARow_ShouldGrowAndAddInCorrectOrder() {
    int toAddCount = 80;
    List<DataElement> elementsToAdd = getRandomElements(toAddCount);
    SeanArrayList<DataElement> sal = new SeanArrayList<>();

    for (DataElement currentElement : elementsToAdd) {
      sal.add(currentElement);
    }

    assertThat(sal.getAllocatedCapacity()).isEqualTo(80);
    assertThat(sal.getSize()).isEqualTo(80);

    for (int i = 0; i < elementsToAdd.size(); i++) {
      assertThat(sal.get(i)).isEqualTo(elementsToAdd.get(i));
    }
  }

  @Test
  public void Add_WithMultipleValuesInTheSameIndex_ShouldGrowAndPushValuesRight() {
    int toAddCount = 81;
    int offset = 6;
    List<DataElement> elementsToAdd = getRandomElements(toAddCount);
    SeanArrayList<DataElement> sal = new SeanArrayList<>();

    for (DataElement currentElement : elementsToAdd) {
      sal.add(currentElement, offset);
    }

    assertThat(sal.getSize()).isEqualTo(toAddCount);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(160);

    for (int i = 0; i < offset; i++) {
      assertThat(elementsToAdd.get(i)).isEqualTo(sal.get(i));
    }

    int reverseCount = 1;
    for (int i = offset; i < elementsToAdd.size(); i++) {
      assertThat(sal.get(i)).isEqualTo(elementsToAdd.get(elementsToAdd.size() - reverseCount));
      reverseCount++;
    }
  }

  @Test
  public void Add_AlwaysAtTheEnd_ShouldNotOverGrowList() {
    int toAddCount = 10;
    List<DataElement> elementsToAdd = getRandomElements(toAddCount);
    SeanArrayList<DataElement> sal = new SeanArrayList<>();

    for (int i = 0; i < elementsToAdd.size(); i++) {
      sal.add(elementsToAdd.get(i), i);
    }

    assertThat(sal.getAllocatedCapacity()).isEqualTo(toAddCount);
    assertThat(sal.getSize()).isEqualTo(toAddCount);
  }

  @Test
  public void Add_AlwaysAtTheBeginning_ShouldReverseInputList() {
    int toAddCount = 100;
    List<DataElement> elementsToAdd = getRandomElements(toAddCount);
    SeanArrayList<DataElement> sal = new SeanArrayList<>();

    for (DataElement currentElement : elementsToAdd) {
      sal.add(currentElement, 0);
    }

    int reverseCount = 1;
    for (int i = 0; i < elementsToAdd.size(); i++) {
      assertThat(sal.get(i)).isEqualTo(elementsToAdd.get(elementsToAdd.size() - reverseCount));
      reverseCount++;
    }

    // triple check that the first value and last values have swapped
    assertThat(sal.get(0)).isEqualTo(elementsToAdd.get(toAddCount - 1));
    assertThat(sal.get(toAddCount - 1)).isEqualTo(elementsToAdd.get(0));
  }

  @Test
  public void Remove_WithNonExistentValue_ShouldReturnNegativeOneForIndex() {
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    int index = sal.remove(DataElement.getRandom());
    int initialElementCount = 20;
    for (int i = 0; i < initialElementCount; i++) {
      sal.add(DataElement.getRandom());
    }

    assertThat(index).isEqualTo(-1);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(initialElementCount);
    assertThat(sal.getSize()).isEqualTo(initialElementCount);
  }

  @Test
  public void Remove_WithNullValue_ShouldReturnNegativeOneForIndex() {
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    int initialElementCount = 20;
    for (int i = 0; i < 20; i++) {
      sal.add(DataElement.getRandom());
    }
    int index = sal.remove(null);

    assertThat(index).isEqualTo(-1);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(initialElementCount);
  }

  @Test
  public void Remove_WithValidValueAtEnd_ShouldRemoveOnlyEndValueAndNotGrowArray() {
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    int initialElementCount = 20;
    for (int i = 0; i < initialElementCount; i++) {
      sal.add(DataElement.getRandom());
    }

    for (int i = initialElementCount - 1; i >= 0; i--) {
      int index = sal.remove(sal.get(sal.getSize() - 1));
      assertThat(index).isEqualTo(sal.getSize());
    }

    assertThat(sal.getSize()).isEqualTo(0);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(DEFAULT_CAPACITY);
  }

  @Test
  public void Remove_WithValidValueAtBeginning_ShouldRemoveOnlyBeginningValueAndNotGrowArray() {
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    int initialElementCount = 31;
    for (int i = 0; i < initialElementCount; i++) {
      sal.add(DataElement.getRandom());
    }

    for (int i = 0; i < initialElementCount; i++) {
      int index = sal.remove(sal.get(0));
      assertThat(index).isEqualTo(0);
    }

    assertThat(sal.getSize()).isEqualTo(0);
    assertThat(sal.getAllocatedCapacity()).isEqualTo(DEFAULT_CAPACITY);
  }

  @Test
  public void Remove_WithValidIndexValues_ShouldRemoveAndReconstituteTheListAccordingly() {
    SeanArrayList<DataElement> sal = new SeanArrayList<>();

    List<Integer> primeNumberList =
        Lists.newArrayList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41);

    int remove1 = 17;
    List<Integer> result1 = Lists.newArrayList(2, 3, 5, 7, 11, 13, 19, 23, 29, 31, 37, 41);

    int remove2 = 41;
    List<Integer> result2 = Lists.newArrayList(2, 3, 5, 7, 11, 13, 19, 23, 29, 31, 37);

    int remove3 = 2;
    List<Integer> result3 = Lists.newArrayList(3, 5, 7, 11, 13, 19, 23, 29, 31, 37);

    int remove4 = 5;
    List<Integer> result4 = Lists.newArrayList(3, 7, 11, 13, 19, 23, 29, 31, 37);

    for (Integer currentPrime : primeNumberList) {
      sal.add(new DataElement(currentPrime));
    }

    sal.remove(new DataElement(remove1));

    for (int i = 0; i < sal.getSize(); i++) {
      assertThat(sal.get(i).getData()).isEqualTo(result1.get(i));
    }

    assertThat(sal.getSize()).isEqualTo(primeNumberList.size() - 1);

    sal.remove(new DataElement(remove2));

    for (int i = 0; i < sal.getSize(); i++) {
      assertThat(sal.get(i).getData()).isEqualTo(result2.get(i));
    }

    assertThat(sal.getSize()).isEqualTo(primeNumberList.size() - 2);

    sal.remove(new DataElement(remove3));

    for (int i = 0; i < sal.getSize(); i++) {
      assertThat(sal.get(i).getData()).isEqualTo(result3.get(i));
    }

    assertThat(sal.getSize()).isEqualTo(primeNumberList.size() - 3);

    sal.remove(new DataElement(remove4));

    for (int i = 0; i < sal.getSize(); i++) {
      assertThat(sal.get(i).getData()).isEqualTo(result4.get(i));
    }

    assertThat(sal.getSize()).isEqualTo(primeNumberList.size() - 4);
  }

  private List<DataElement> getRandomElements(int length) {
    List<DataElement> elements = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      elements.add(DataElement.getRandom());
    }

    return elements;
  }
}
