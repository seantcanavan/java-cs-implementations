package seantcanavan;

import java.util.List;

public class SeanQuickSort<T extends Comparable<T>> {

  private List<T> values;
  private int length;

  public void quickSort(List<T> values) {
    this.values = values;
    quickSort(0, values.size() - 1);
  }

  private void quickSort(int lowerIndex, int higherIndex) {

    int i = lowerIndex;
    int j = higherIndex;
    // calculate pivot number, I am taking pivot as middle index number
    T pivot = values.get(lowerIndex+(higherIndex-lowerIndex)/2);
    // Divide into two arrays
    while (i <= j) {
      while (values.get(i).compareTo(pivot) < 0) {
        i++;
      }
      while (values.get(i).compareTo(pivot) > 0) {
        j--;
      }
      if (i <= j) {
        exchangeNumbers(i, j);
        //move index to next position on both sides
        i++;
        j--;
      }
    }

    // call quickSort() method recursively
    if (lowerIndex < j)
      quickSort(lowerIndex, j);
    if (i < higherIndex)
      quickSort(i, higherIndex);
  }

  private void exchangeNumbers(int i, int j) {
    T current = values.get(i);
    values.set(i, values.get(j));
    values.set(j, current);
  }
}