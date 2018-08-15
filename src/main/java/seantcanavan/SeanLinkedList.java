package seantcanavan;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class SeanLinkedList<E> implements Serializable {

  private static class Node<E> {
    public E data;

    public Node<E> next;
    public Node<E> prev;

    public Node(E data) {
      this.data = data;
    }

    @Override
    public String toString() {
      StringBuilder returnThis = new StringBuilder();
      if (this.prev == null) returnThis.append("begin");
      else returnThis.append(this.prev.data);

      returnThis.append(" <- ");
      returnThis.append(this.data);
      returnThis.append(" -> ");
      if (this.next == null) returnThis.append("end");
      else returnThis.append(this.next.data);
      return returnThis.toString();
    }
  }

  private static final long serialVersionUID = -3896129847029825570L;
  private Node<E> first;
  private Node<E> last;

  private int size;

  public void add(E newElement) {
    this.addLast(newElement);
  }

  public void addFirst(E newElement) {
    Node<E> current = this.first;
    this.first = new Node<E>(newElement);
    this.first.next = current;
    if (this.size != 0) this.first.next.prev = this.first;
    if (this.size == 0) this.last = this.first;
    this.size += 1;
  }

  public void addLast(E newElement) {
    Node<E> newNode = new Node<E>(newElement);
    this.last.next = newNode;
    newNode.prev = this.last;
    this.last = newNode;
    if (this.size == 0) this.first = newNode;
    this.size += 1;
  }

  public void clear() {
    this.first = null;
    this.last = null;
    this.size = -1;
  }

  public boolean contains(E value) {
    if (value == null) return false;
    if (this.indexOf(value) == -1) return false;
    return true;
  }

  public E get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("List does not have an element at position: " + index);

    double portion = ((double) index / (double) this.size);
    Node<E> current;
    int traversed = 0;

    if (portion >= .50) {
      int position = this.size - index;
      current = this.last;
      for (int x = 0; x < position; x++) {
        current = current.prev;
        traversed++;
      }
    } else {
      int position = index;
      current = this.first;
      for (int x = 0; x < position; x++) {
        current = current.next;
        traversed++;
      }
    }
    System.out.println("traversed: " + traversed);
    return current.data;
  }

  public E getFirst() throws NoSuchElementException {
    if (this.first == null) throw new NoSuchElementException("The list is not initialized");
    return first.data;
  }

  public E getLast() throws NoSuchElementException {
    if (this.first == null) throw new NoSuchElementException("The list is not initialized");
    return this.last.data;
  }

  public int indexOf(Object value) {
    if (this.first == null) return -1;

    Node<E> current = this.first;
    int position = 0;
    do {
      if (value.equals(current.data)) return position;
      position += 1;
      current = current.next;
    } while (current.next != null);
    return -1;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public void printSelf() {
    if (this.size != 0) {
      System.out.println("size: " + this.size);
      System.out.println("first: " + this.first.data);
      System.out.println("last: " + this.last.data);
      Node<E> current = this.first;
      while (current.next != null) {
        System.out.println(current.toString());
        current = current.next;
      }
      System.out.println(current);
    } else System.out.println("list is empty");
  }

  public boolean remove(Object oldValue) {
    Node<E> current = this.first;
    while (current != null) {
      if (oldValue.equals(current.data)) {
        current.prev.next = current.next;
        current.next.prev = current.prev;
        current.next = null;
        current.prev = null;
        this.size -= 1;
        return true;
      }
      current = current.next;
    }
    return false;
  }

  public E removeFirst() throws NoSuchElementException {
    if (this.size == 0) throw new NoSuchElementException("list is empty, can't remove first");

    E returnThis = this.first.data;
    this.first = this.first.next;
    this.first.prev = null;
    this.size -= 1;
    return returnThis;
  }

  public E removeLast() throws NoSuchElementException {
    if (this.size == 0) throw new NoSuchElementException("list is empty, can't remove last");

    Node<E> lastElement = this.last;
    this.last = this.last.prev;
    this.last.next = null;
    lastElement.prev = null;
    lastElement.next = null;
    this.size -= 1;
    return lastElement.data;
  }

  public void reverse() {
    Node<E> oldFirst = this.first;
    Node<E> oldLast = this.last;
    Node<E> current = this.first;

    for (int x = 0; x < this.size; x++) {
      Node<E> next = current.next;

      Node<E> curNext = current.next;
      Node<E> curPrev = current.prev;

      current.prev = curNext;
      current.next = curPrev;

      current = next;
    }

    this.last = oldFirst;
    this.first = oldLast;
  }

  public int size() {
    return this.size;
  }
}
