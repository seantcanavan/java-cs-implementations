package seantcanavan;

import seantcanavan.components.DoubleLinkNode;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class SeanLinkedList<T extends Comparable<T>> implements Serializable {

  private static final long serialVersionUID = -3896129847029825570L;
  private DoubleLinkNode<T> first;
  private DoubleLinkNode<T> last;

  private int size;

  public void add(T newElement) {
    this.addLast(newElement);
  }

  public void addFirst(T newElement) {
    DoubleLinkNode<T> current = this.first;
    this.first = new DoubleLinkNode<T>(newElement);
    this.first.setRight(current);

    if (this.size != 0) {
      this.first.getRight().setLeft(this.first);
    }

    if (this.size == 0) {
      this.last = this.first;
    }

    this.size += 1;
  }

  public void addLast(T newElement) {
    DoubleLinkNode<T> newNode = new DoubleLinkNode<>(newElement);
    this.last.setRight(newNode);
    newNode.setLeft(this.last);
    this.last = newNode;

    if (this.size == 0) {
      this.first = newNode;
    }

    this.size += 1;
  }

  public void clear() {
    this.first = null;
    this.last = null;
    this.size = -1;
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
      int position = this.size - index;
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
    if (this.first == null) return -1;

    DoubleLinkNode<T> current = this.first;
    int position = 0;
    do {
      if (value.equals(current.getData())) return position;
      position += 1;
      current = current.getRight();
    } while (current.getRight() != null);
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

  public boolean remove(Object oldValue) {
    DoubleLinkNode<T> current = this.first;
    while (current != null) {
      if (oldValue.equals(current.getData())) {
        current.getLeft().setRight(current.getRight());
        current.getRight().setLeft(current.getLeft());
        current.setRight(null);
        current.setLeft(null);
        this.size -= 1;
        return true;
      }
      current = current.getRight();
    }
    return false;
  }

  public T removeFirst() throws NoSuchElementException {
    if (this.size == 0) throw new NoSuchElementException("list is empty, can't remove first");

    T returnThis = this.first.getData();
    this.first = this.first.getLeft();
    this.first.setLeft(null);
    this.size -= 1;
    return returnThis;
  }

  public T removeLast() throws NoSuchElementException {
    if (this.size == 0) throw new NoSuchElementException("list is empty, can't remove last");

    DoubleLinkNode<T> lastElement = this.last;
    this.last = this.last.getLeft();
    this.last.setRight(null);
    lastElement.setLeft(null);
    lastElement.setRight(null);
    this.size -= 1;
    return lastElement.getData();
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

  public int size() {
    return this.size;
  }
}
