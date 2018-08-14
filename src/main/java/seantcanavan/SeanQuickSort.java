package seantcanavan;

import java.util.List;
import java.util.Random;

public class SeanQuickSort {

	private boolean increasing;

	public SeanQuickSort(Boolean increasing) {
		this.increasing = increasing;
	}

	public String printList(List<Integer> inputList) {
		StringBuilder sb = new StringBuilder();

		for (Integer currentInteger : inputList) {
			sb.append("\t");
			sb.append(currentInteger.toString());
			sb.append("\t");
		}

		return sb.toString();
	}

	private int partitionList(List<Integer> values, int start, int end) {
		Random pivotGenerator = new Random(System.currentTimeMillis()); // seed a new random number generator

		// generate a random pivot index from [start, end]
		int randomPivotIndex = pivotGenerator.nextInt(end);
		if (randomPivotIndex < start) {
			randomPivotIndex += start;
			if (randomPivotIndex >= end) {
				randomPivotIndex = end - 1;
			}
		}

		this.swapValues(values, randomPivotIndex, end); // swap the random value to the end

		Integer pivotValue = values.get(end);
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

	public void quickSort(List<Integer> values, int start, int end) {
		if (start < end) {
			int pivotIndex = this.partitionList(values, start, end);
			this.quickSort(values, start, pivotIndex - 1);
			this.quickSort(values, pivotIndex + 1, end);
		}
	}

	private void swapValues(List<Integer> values, int firstIndex, int secondIndex) {
		Integer firstValue = values.get(firstIndex);
		Integer secondValue = values.get(secondIndex);

		values.set(firstIndex, secondValue);
		values.set(secondIndex, firstValue);
	}
}