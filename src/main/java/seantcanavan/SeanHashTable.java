package seantcanavan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class SeanHashTable {
  private static class HashValue {
    private String key;
    private String value;

    public HashValue(String key, String value) {
      if (key == null || value == null)
        throw new IllegalArgumentException("no null values allowed");
      this.key = key;
      this.value = value;
    }

    @Override
    public String toString() {
      StringBuilder returnThis = new StringBuilder();
      returnThis.append("key: ");
      returnThis.append(key);
      returnThis.append("  ");
      returnThis.append("value: ");
      returnThis.append(value);
      return returnThis.toString();
    }
  }

  private final int ARRAY_LIST_SIZE = 25;
  private int size;

  private ArrayList<LinkedList<HashValue>> table = new ArrayList<LinkedList<HashValue>>();

  public SeanHashTable() {
    for (int x = 0; x < ARRAY_LIST_SIZE; x++) table.add(new LinkedList<HashValue>());
    this.size = 0;
  }

  public void clear() {
    for (int x = this.table.size() - 1; x >= 0; x--) this.table.get(x).clear();
    this.size = 0;
  }

  private String flattenLinkedList(LinkedList<HashValue> list) {
    StringBuffer returnThis = new StringBuffer();
    returnThis.append("list: ");
    for (HashValue currentValue : list) {
      returnThis.append(currentValue.toString());
      returnThis.append("   ");
    }
    return returnThis.toString();
  }

  public String get(String key) {
    int hash = (myhash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    if (currentList.size() == 0) return null;

    Iterator<HashValue> currentIterator = currentList.iterator();
    while ((currentIterator.hasNext())) {
      HashValue currentElement = currentIterator.next();
      if (currentElement.key.compareTo(key) == 0) return currentElement.value;
    }
    return null;
  }

  public int getSize() {
    return this.size;
  }

  public void insert(String key, String value) {
    HashValue newValue = new HashValue(key, value);
    int hash = (myhash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    Iterator<HashValue> currentIterator = currentList.iterator();
    boolean found = false;
    while (currentIterator.hasNext()) {
      HashValue currentElement = currentIterator.next();
      if (currentElement.key.compareTo(key) == 0) {
        currentElement.value = value;
        found = true;
        break;
      }
    }

    if (!found) {
      currentList.addLast(newValue);
      this.size++;
    }
  }

  private int myhash(String x) {
    int hashVal = x.hashCode();
    hashVal %= this.ARRAY_LIST_SIZE;
    if (hashVal < 0) hashVal += this.ARRAY_LIST_SIZE;
    return hashVal;
  }

  public void printHashTable() {
    for (LinkedList<HashValue> currentList : this.table) {
      System.out.println(this.flattenLinkedList(currentList));
    }
  }

  public boolean remove(String key) {
    int hash = (myhash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    if (currentList.size() == 0) return false;
    Iterator<HashValue> currentIterator = currentList.iterator();
    int index = 0; // prevents currentList.getIndex() call to avoid n^2 loop
    while (currentIterator.hasNext()) {
      HashValue currentValue = currentIterator.next();
      if (currentValue.key.compareTo(key) == 0) {
        currentList.remove(index);
        this.size--;
        return true;
      }
      index++;
    }
    return false;
  }
}
