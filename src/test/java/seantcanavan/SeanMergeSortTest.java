package seantcanavan;

import java.util.ArrayList;
import java.util.List;

public class SeanMergeSortTest {

	public static void main(String[] args) {
		List<Integer> values = new ArrayList<Integer>();
		values.add(3);
		values.add(2);
		values.add(1);
		values.add(9);
		values.add(7);
		values.add(8);
		values.add(6);
		values.add(5);
		values.add(4);
		values.add(0);

		SeanMergeSort sms = new SeanMergeSort();
		sms.mergeSort(values);

		for (Integer currentInteger : values)
			System.out.println(currentInteger);
	}
}
