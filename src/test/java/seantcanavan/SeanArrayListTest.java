package seantcanavan;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.fest.assertions.api.Assertions.assertThat;

public class SeanArrayListTest {

  private static class DataElement implements Comparable<DataElement> {

    private int data;

    public DataElement() {}

    public DataElement(int data) {
      this.data = data;
    }

    public int getData() {
      return data;
    }

    public void setData(int data) {
      this.data = data;
    }

    public static DataElement getRandom() {
      return new DataElement(new SecureRandom().nextInt(10000));
    }

    @Override
    public int compareTo(DataElement dataElement) {
      return this.data - dataElement.getData();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      DataElement that = (DataElement) o;
      return data == that.data;
    }

    @Override
    public int hashCode() {
      return Objects.hash(data);
    }
  }

  @Test
  public void Constructor_WithAllConstructorArguments_ShouldCorrectlyInitializeSeanArrayList() {
    // empty constructor
    SeanArrayList<DataElement> sal = new SeanArrayList<>();
    assertThat(sal.getAllocatedCapacity()).isEqualTo(SeanArrayList.DEFAULT_CAPACITY);

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
    assertThat(sal.getAllocatedCapacity())
        .isEqualTo(SeanArrayList.DEFAULT_CAPACITY * 2 * reallocationCount);
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

  private List<DataElement> getRandomElements(int length) {
    List<DataElement> elements = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      elements.add(DataElement.getRandom());
    }

    return elements;
  }
}
