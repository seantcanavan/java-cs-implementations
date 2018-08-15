package seantcanavan;

import java.util.Collection;

public class SeanArrayList<T extends Comparable> {
  public static final int DEFAULT_CAPACITY = 10;
  public static final int GROWTH_RATE = 2;
  public static final int SHRINK_RATE = 3;
  private int size = 0;
  private Object[] list = new Object[SeanArrayList.DEFAULT_CAPACITY];

  public SeanArrayList() {}

  public SeanArrayList(int initialSize) {
    if (initialSize < 1) {
      throw new IllegalArgumentException("Initial list size must be positive");
    }

    this.list = new Object[initialSize];
  }

  public SeanArrayList(Collection<T> collection) {
    this.addAll(collection);
  }

  public void addAll(Collection<T> collection) {
    for (T val : collection) {
      this.add(val);
    }
  }

  public void add(T value) {
    // always add to the end by default
    this.add(value, size);
  }

  public void add(T value, int index) {

    if (index < 0) {
      throw new IllegalArgumentException("Can't add at negative index.");
    }

    if (index > size) {
      index = size;
    }

    // guarantee there's enough space to move everything to the right one space
    this.ensureCapacity();

    // start from the back and move every element to the right once
    for (int i = size; i > index; i--) {
      list[i] = list[i - 1];
    }

    // insert the value in the new space
    list[index] = value;
    this.size++;
  }

  public void remove(T value) {}

  public void removeAt(int index) {}

  @SuppressWarnings("unchecked")
  public T get(int index) {
    if (index > size) {
      return null;
    }

    return (T) list[index];
  }

  private void ensureCapacity() {
    if (this.size < list.length) {
      return;
    }

    Object[] newArray = new Object[list.length * GROWTH_RATE];
    System.arraycopy(list, 0, newArray, 0, list.length);
    list = newArray;
  }

  private void shrinkCapacity() {}

  public int getSize() {
    return size;
  }

  public int getAllocatedCapacity() {
    return list.length;
  }
}
