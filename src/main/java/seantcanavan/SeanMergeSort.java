package seantcanavan;

import java.util.ArrayList;
import java.util.List;

public class SeanMergeSort {

	private void merge(List<Integer> left, List<Integer> right, List<Integer> original) {
		int leftLength = left.size();
		int rightLength = right.size();
		int leftPosition = 0;
		int rightPosition = 0;
		int originalPosition = 0;

		while (leftPosition < leftLength && rightPosition < rightLength) {
			if (left.get(leftPosition).compareTo(right.get(rightPosition)) < 0) {
				original.set(originalPosition, left.get(leftPosition));
				leftPosition++;
			} else if (right.get(rightPosition).compareTo(left.get(leftPosition)) <= 0) {
				original.set(originalPosition, right.get(rightPosition));
				rightPosition++;
			}
			originalPosition++;
		}

		while (leftPosition < leftLength) {
			original.set(originalPosition, left.get(leftPosition));
			leftPosition++;
			originalPosition++;
		}
		while (rightPosition < rightLength) {
			original.set(originalPosition, right.get(rightPosition));
			rightPosition++;
			originalPosition++;
		}
	}

	public void mergeSort(List<Integer> values) {
		if (values.size() < 2)
			return;
		int middle = values.size() / 2;
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		for (int x = 0; x < middle; x++)
			left.add(values.get(x));
		for (int y = middle; y < values.size(); y++)
			right.add(values.get(y));
		this.mergeSort(left);
		this.mergeSort(right);
		this.merge(left, right, values);
	}
}