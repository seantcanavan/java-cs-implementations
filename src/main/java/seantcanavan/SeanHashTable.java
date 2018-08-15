package seantcanavan;

import seantcanavan.components.HashValue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class SeanHashTable {
  private final int ARRAY_LIST_SIZE = 25;
  private int size;

  private ArrayList<LinkedList<HashValue>> table = new ArrayList<>();

  public SeanHashTable() {
    for (int x = 0; x < ARRAY_LIST_SIZE; x++) table.add(new LinkedList<>());
    this.size = 0;
  }

  public void clear() {
    for (int x = this.table.size() - 1; x >= 0; x--) this.table.get(x).clear();
    this.size = 0;
  }

  private String flattenLinkedList(LinkedList<HashValue> list) {
    StringBuilder returnThis = new StringBuilder();
    returnThis.append("list: ");
    for (HashValue currentValue : list) {
      returnThis.append(currentValue.toString());
      returnThis.append("   ");
    }
    return returnThis.toString();
  }

  public String get(String key) {
    int hash = (calculateHash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    if (currentList.size() == 0) return null;

    Iterator<HashValue> currentIterator = currentList.iterator();
    while ((currentIterator.hasNext())) {
      HashValue currentElement = currentIterator.next();
      if (currentElement.getKey().compareTo(key) == 0) return currentElement.getValue();
    }
    return null;
  }

  public int getSize() {
    return this.size;
  }

  public void insert(String key, String value) {
    HashValue newValue = new HashValue(key, value);
    int hash = (calculateHash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    Iterator<HashValue> currentIterator = currentList.iterator();
    boolean found = false;
    while (currentIterator.hasNext()) {
      HashValue currentElement = currentIterator.next();
      if (currentElement.getKey().compareTo(key) == 0) {
        currentElement.setValue(value);
        found = true;
        break;
      }
    }

    if (!found) {
      currentList.addLast(newValue);
      this.size++;
    }
  }

  private int calculateHash(String x) {
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
    int hash = (calculateHash(key) % this.ARRAY_LIST_SIZE);
    LinkedList<HashValue> currentList = this.table.get(hash);
    if (currentList.size() == 0) return false;
    Iterator<HashValue> currentIterator = currentList.iterator();
    int index = 0; // prevents currentList.getIndex() call to avoid n^2 loop
    while (currentIterator.hasNext()) {
      HashValue currentValue = currentIterator.next();
      if (currentValue.getKey().compareTo(key) == 0) {
        currentList.remove(index);
        this.size--;
        return true;
      }
      index++;
    }
    return false;
  }
}
