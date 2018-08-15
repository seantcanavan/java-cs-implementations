package seantcanavan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SeanQuickSortTest {

  @Test
  public void Quicksort_WithStandardSetOfValues_ShouldReturnListInSortedOrder() {
    ArrayList<Integer> unsortedList = new ArrayList<>();
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
    SeanQuickSort qs = new SeanQuickSort(true);
    qs.quickSort(unsortedList, 0, unsortedList.size() - 1);
    System.out.println(qs.printList(unsortedList));

    qs = new SeanQuickSort(false);
    qs.quickSort(unsortedList, 0, unsortedList.size() - 1);
    System.out.println(qs.printList(unsortedList));
  }
//
//  @Test
//  public void
}
