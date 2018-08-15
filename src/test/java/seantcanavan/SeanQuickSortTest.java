package seantcanavan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SeanQuickSortTest {

  @Test
  public void Quicksort_WithStandardSetOfValues_ShouldReturnListInSortedOrder() {
    List<Integer> unsortedList = new ArrayList<>();
    unsortedList.add(3);
    unsortedList.add(4);
    unsortedList.add(7);
    unsortedList.add(1);
    unsortedList.add(2);
    unsortedList.add(6);
    unsortedList.add(5);
    unsortedList.add(9);
    unsortedList.add(8);
    unsortedList.add(0);
    SeanQuickSort<Integer> qs = new SeanQuickSort<>();
//    qs.quickSort(unsortedList);
//
//    qs = new SeanQuickSort<>();
//    qs.quickSort(unsortedList);
  }
}
