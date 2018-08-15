package seantcanavan;

import seantcanavan.components.DoubleLinkNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class SeanLinkedList<T extends Comparable<T>> {

  private DoubleLinkNode<T> first;
  private DoubleLinkNode<T> last;
  private int size;

  public void add(T data) {
    this.addLast(data);
  }

  public void addFirst(T data) {
    DoubleLinkNode<T> newFirst = new DoubleLinkNode<>(data);
    DoubleLinkNode<T> currentFirst = this.first;

    if (currentFirst == null) {
      this.first = newFirst;
      this.last = newFirst;
    } else {
      this.first = newFirst;
      this.first.setRight(currentFirst);
      currentFirst.setLeft(first);
    }

    this.size++;
  }

  public void addLast(T data) {
    DoubleLinkNode<T> newLast = new DoubleLinkNode<>(data);
    DoubleLinkNode<T> currentLast = last;

    if (currentLast == null) {
      this.first = newLast;
      this.last = newLast;
    } else {
      currentLast.setRight(newLast);
      newLast.setLeft(currentLast);
      this.last = newLast;
    }

    this.size++;
  }

  public void clear() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public boolean contains(T value) {
    if (value == null) {
      return false;
    }

    return this.indexOf(value) != -1;
  }

  public T get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("List does not have an element at position: " + index);
    }

    double portion = ((double) index / (double) this.size);
    DoubleLinkNode<T> current;
    int traversed = 0;

    if (portion >= .50) {
      int position = this.size - 1 - index;
      current = this.last;
      for (int x = 0; x < position; x++) {
        current = current.getLeft();
        traversed++;
      }
    } else {
      current = this.first;
      for (int x = 0; x < index; x++) {
        current = current.getRight();
        traversed++;
      }
    }
    System.out.println("traversed: " + traversed);
    return current.getData();
  }

  public T getFirst() throws NoSuchElementException {
    if (this.first == null) {
      throw new NoSuchElementException("The list is not initialized");
    }

    return first.getData();
  }

  public T getLast() throws NoSuchElementException {
    if (this.first == null) {
      throw new NoSuchElementException("The list is not initialized");
    }

    return this.last.getData();
  }

  public int indexOf(Object value) {

    if (value == null) {
      return -1;
    }

    if (this.first == null) {
      return -1;
    }

    DoubleLinkNode<T> current = this.first;
    int position = 0;
    while (current != null) {
      if (value.equals(current.getData())) {
        return position;
      }
      position++;
      current = current.getRight();
    }

    // not found
    return -1;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public void printSelf() {
    if (this.size != 0) {
      System.out.println("size: " + this.size);
      System.out.println("first: " + this.first.getData());
      System.out.println("last: " + this.last.getData());
      DoubleLinkNode<T> current = this.first;
      while (current.getRight() != null) {
        System.out.println(current.toString());
        current = current.getRight();
      }
      System.out.println(current);
    } else System.out.println("list is empty");
  }

  public boolean remove(T value) {
    if (this.first == null) {
      throw new NoSuchElementException("Can't remove from an empty list");
    }

    DoubleLinkNode<T> current = this.first;

    while (!current.getData().equals(value)) {
      if (current.getRight() == null) {
        return false;
      }

      current = current.getRight();
    }

    if (this.size == 1) {
      this.clear();
    } else if (this.size == 2) {
      if (current.equals(this.first)) {
        this.first = last;
        this.first.setLeft(null);
        this.first.setRight(null);
      } else {
        this.last = first;
        this.last.setLeft(null);
        this.last.setRight(null);
      }
      this.size = 1;
    } else {
      DoubleLinkNode<T> leftLink = current.getLeft();
      DoubleLinkNode<T> rightLink = current.getRight();

      leftLink.setRight(rightLink);
      rightLink.setLeft(leftLink);
      this.size--;
    }

    return true;
  }

  public T removeFirst() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Can't remove first element when list is empty.");
    } else if (size == 1) {
      T data = this.last.getData();
      this.clear();
      return data;
    }

    T data = this.first.getData();

    this.first = this.first.getRight();
    this.first.setLeft(null);

    this.size--;
    return data;
  }

  public T removeLast() throws NoSuchElementException {
    if (size == 0) {
      throw new NoSuchElementException("Can't remove last element when list is empty.");
    } else if (size == 1) {
      T data = this.first.getData();
      this.clear();
      return data;
    }

    T data = this.last.getData();

    DoubleLinkNode<T> newLast = this.last.getLeft();
    newLast.setRight(null);
    this.last = newLast;

    this.size--;
    return data;
  }

  public void reverse() {
    DoubleLinkNode<T> oldFirst = this.first;
    DoubleLinkNode<T> oldLast = this.last;
    DoubleLinkNode<T> current = this.first;

    for (int x = 0; x < this.size; x++) {
      DoubleLinkNode<T> next = current.getRight();

      DoubleLinkNode<T> curNext = current.getRight();
      DoubleLinkNode<T> curPrev = current.getLeft();

      current.setLeft(curNext);
      current.setRight(curPrev);

      current = next;
    }

    this.last = oldFirst;
    this.first = oldLast;
  }

  public List<T> toList() {
    List<T> list = new ArrayList<>();
    DoubleLinkNode<T> current = this.first;
    while (current != null) {
      list.add(current.getData());
      current = current.getRight();
    }

    return list;
  }

  public int getSize() {
    return size;
  }

  /**
   * Requires equals override on T
   */
  public boolean isValid() {
    if (first == null) {
      return true;
    }

    int traversed = 1;
    DoubleLinkNode<T> current = first;
    DoubleLinkNode<T> next = current.getRight();

    while (next != null) {
      if (!current.getRight().equals(next)) {
        return false;
      }

      if (!current.getRight().getLeft().equals(current)) {
        return false;
      }

      current = next;
      next = current.getRight();
      traversed++;
    }

    if (!current.equals(this.last)) {
      return false;
    }

    return traversed == size;
  }
}
