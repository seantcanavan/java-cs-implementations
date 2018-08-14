package seantcanavan;

import java.io.Serializable;
import java.util.Collection;

public class SeanArrayList<E> implements Serializable {
	private static final int DEFAULT_CAPACITY = 10;
	private static final int GROWTH_RATE = 2;
	private static final int SHRINK_RATE = 3;
	private int size = -1;
	private E[] list;

	public SeanArrayList(int initialSize) {
		if (initialSize < 1) {
			throw new IllegalArgumentException("Initial list size must be positive");
		}

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[initialSize];
		this.list = newArray;
	}

	public SeanArrayList() {
		this(SeanArrayList.DEFAULT_CAPACITY);
	}

	public SeanArrayList(Collection<? extends E> collection) {
		this(collection.size() + (collection.size() / 2));
		this.addAll(collection);
	}

	public void addAll(Collection<? extends E> collection) {
		for (E val : collection) {
			this.add(val);
		}
	}

	public void add(E value) {
		if (this.list.length == this.size) {
			this.growCapacity(this.list.length + 1);
		}

		this.list[size] = value;
		this.size++;
	}

	public void remove(E value) {
		// the ratio of items in the list versus the capacity of the list
		double ratio = this.list.length / this.size;
		if (ratio < 0.67) {
			this.shrinkCapacity();
		}

		this.size--;
	}

	public void growCapacity(int minSize) {

		@SuppressWarnings("unchecked")
		E[] newArray = (E[]) new Object[minSize * 2];

		for(int x = 0; x < this.list.length; x++) {
			newArray[x] = this.list[x];
		}

		this.list = newArray;
	}

	public void shrinkCapacity() {

	}
}
