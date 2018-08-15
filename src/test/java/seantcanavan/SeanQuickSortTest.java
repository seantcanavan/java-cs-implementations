package seantcanavan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SeanQuickSortTest {

  @Test
  public void Should_Test() {
    ArrayList<Integer> unsortedList = new ArrayList<Integer>();
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
    for (Integer currentInteger : unsortedList) System.out.println(currentInteger.toString());
  }
}
