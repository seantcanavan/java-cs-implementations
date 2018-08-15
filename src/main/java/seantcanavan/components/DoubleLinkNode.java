package seantcanavan.components;

import java.util.Objects;

public class DoubleLinkNode<T extends Comparable<T>> {
  private DoubleLinkNode<T> left;
  private DoubleLinkNode<T> right;
  private T data;

  public DoubleLinkNode(T data) {
    this.data = data;
  }

  public DoubleLinkNode(T data, DoubleLinkNode<T> left, DoubleLinkNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public DoubleLinkNode<T> getLeft() {
    return left;
  }

  public void setLeft(DoubleLinkNode<T> left) {
    this.left = left;
  }

  public DoubleLinkNode<T> getRight() {
    return right;
  }

  public void setRight(DoubleLinkNode<T> right) {
    this.right = right;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (left != null && left.getData() != null) {
      sb.append(left.getData());
      sb.append(" <= ");
    }

    sb.append(" ");
    if (data != null) {
      sb.append(data.toString());
    }
    sb.append(" ");

    if (right != null && right.getData() != null) {
      sb.append(" => ");
      sb.append(right.getData());
    }

    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DoubleLinkNode<?> that = (DoubleLinkNode<?>) o;
    return Objects.equals(left, that.left) &&
            Objects.equals(right, that.right) &&
            Objects.equals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(left, right, data);
  }
}
