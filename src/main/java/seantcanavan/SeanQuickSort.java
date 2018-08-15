package seantcanavan;

import java.util.List;
import java.util.Random;

public class SeanQuickSort<T extends Comparable<T>> {

  private boolean increasing;

  public SeanQuickSort(Boolean increasing) {
    this.increasing = increasing;
  }

  public String printList(List<T> inputList) {
    StringBuilder sb = new StringBuilder();

    for (T currentValue : inputList) {
      sb.append("\t");
      sb.append(currentValue.toString());
      sb.append("\t");
    }

    return sb.toString();
  }

  private int partitionList(List<T> values, int start, int end) {
    Random pivotGenerator =
        new Random(System.currentTimeMillis()); // seed a new random number generator

    // generate a random pivot index from [start, end]
    int randomPivotIndex = pivotGenerator.nextInt(end);
    if (randomPivotIndex < start) {
      randomPivotIndex += start;
      if (randomPivotIndex >= end) {
        randomPivotIndex = end - 1;
      }
    }

    this.swapValues(values, randomPivotIndex, end); // swap the random value to the end

    T pivotValue = values.get(end);
    System.out.print("pivotValue: " + pivotValue + "\t");
    int partitionPosition = start;

    for (int i = start; i <= end; i++) {
      if ((increasing && values.get(i).compareTo(pivotValue) < 0)
          || (!increasing && values.get(i).compareTo(pivotValue) > 0)) {
        this.swapValues(values, i, partitionPosition);
        partitionPosition++;
      }
    }
    this.swapValues(values, partitionPosition, end);
    System.out.println(this.printList(values));
    return partitionPosition;
  }

  public void quickSort(List<T> values, int start, int end) {
    if (start < end) {
      int pivotIndex = this.partitionList(values, start, end);
      this.quickSort(values, start, pivotIndex - 1);
      this.quickSort(values, pivotIndex + 1, end);
    }
  }

  private void swapValues(List<T> values, int firstIndex, int secondIndex) {
    T firstValue = values.get(firstIndex);
    T secondValue = values.get(secondIndex);

    values.set(firstIndex, secondValue);
    values.set(secondIndex, firstValue);
  }
}
