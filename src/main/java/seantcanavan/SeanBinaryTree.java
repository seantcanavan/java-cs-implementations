package seantcanavan;

import seantcanavan.components.DoubleLinkNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SeanBinaryTree<T extends Comparable<T>> {

  private DoubleLinkNode<T> root;
  private final Order order;

  enum Order {
    PRE_ORDER,
    IN_ORDER,
    POST_ORDER,
  }

  public SeanBinaryTree() {
    this.order = Order.IN_ORDER;
  }

  public SeanBinaryTree(Order order) {
    this.order = order;
  }

  public DoubleLinkNode<T> add(T data) {
    DoubleLinkNode<T> newNode = new DoubleLinkNode<>(data);

    if (root == null) {
      root = newNode;
      return root;
    }

    DoubleLinkNode<T> current = root;
    while (current.getData() != null) {
      if (current.getData().compareTo(data) > 0) {
        if (current.getLeft() == null) {
          current.setLeft(newNode);
          return newNode;
        } else {
          current = current.getLeft();
        }
      } else if (current.getData().compareTo(data) < 0) {
        if (current.getRight() == null) {
          current.setRight(newNode);
          return newNode;
        } else {
          current = current.getRight();
        }
      } else {
        return newNode;
      }
    }

    throw new RuntimeException("Unreachable.");
  }

  public DoubleLinkNode<T> find(T data) {
    if (root == null) {
      return null;
    }

    DoubleLinkNode<T> current = root;
    while (current.getData() != null) {
      if (current.getData().compareTo(data) == 0) {
        return current;
      } else if (current.getData().compareTo(data) > 0) {
        current = current.getLeft();
      } else {
        current = current.getRight();
      }
    }

    // not found
    return null;
  }

  public DoubleLinkNode<T> delete(T data) {
    return delete(root, data);
  }

  private DoubleLinkNode<T> delete(DoubleLinkNode<T> current, T data) {
    if (current == null) {
      return null;
    }

    if (data.compareTo(current.getData()) < 0) {
      current.setLeft(delete(current.getLeft(), data));
    } else if (data.compareTo(current.getData()) > 0) {
      current.setRight(delete(current.getRight(), data));
    } else {
      // tackle leaf and single node at the same time
      if (current.getLeft() == null) {
        return current.getRight();
      } else if (current.getRight() == null) {
        return current.getLeft();
      } else {
        DoubleLinkNode<T> replacement = findMax(current.getLeft());
        current.setData(replacement.getData());

        current.setLeft(delete(current.getLeft(), replacement.getData()));
      }
    }

    // not found
    return null;
  }

  private DoubleLinkNode<T> findMax(DoubleLinkNode<T> root) {
    while (root.getRight() != null) {
      root = root.getRight();
    }

    return root;
  }

  public void printBreadthFirst() {
    if (root == null) {
      return;
    }

    Queue<DoubleLinkNode<T>> queue = new ConcurrentLinkedQueue<>();
    queue.offer(root);

    while (queue.peek() != null) {
      DoubleLinkNode<T> current = queue.poll();
      System.out.print(current + "\n");
      if (current.getLeft() != null) {
        queue.offer(current.getLeft());
      }

      if (current.getRight() != null) {
        queue.offer(current.getRight());
      }
    }
  }

  public List<T> printDepthFirst() {
    return printDepthFirst(this.root, new LinkedList<>());
  }

  private List<T> printDepthFirst(DoubleLinkNode<T> root, List<T> elements) {
    if (root == null) {
      return elements;
    }

    if (Order.PRE_ORDER.equals(this.order)) {
      System.out.println(root + "\n");
      elements.add(root.getData());
    }

    if (root.getLeft() != null) {
      printDepthFirst(root.getLeft(), elements);
    }

    if (Order.IN_ORDER.equals(this.order)) {
      System.out.print(root + "\n");
      elements.add(root.getData());
    }

    if (root.getRight() != null) {
      printDepthFirst(root.getRight(), elements);
    }

    if (Order.POST_ORDER.equals(this.order)) {
      System.out.println(root + "\n");
      elements.add(root.getData());
    }

    return elements;
  }

  public boolean validTree() {
    return validTree(this.root);
  }

  private boolean validTree(DoubleLinkNode<T> root) {
    if (root == null) {
      return true;
    }

    Queue<DoubleLinkNode<T>> queue = new ConcurrentLinkedQueue<>();
    queue.offer(root);
    while (queue.peek() != null) {
      DoubleLinkNode<T> current = queue.poll();
      if (current.getLeft() != null) {
        if (current.getLeft().getData().compareTo(current.getData()) >= 0) {
          System.out.println("current:");
          System.out.println(current);
          System.out.println("one:");
          System.out.println(current.getLeft());
          return false;
        }

        queue.offer(current.getLeft());
      }

      if (current.getRight() != null) {
        if (current.getRight().getData().compareTo(current.getData()) <= 0) {
          System.out.println("current:");
          System.out.println(current);
          System.out.println("two: ");
          System.out.println(current.getRight());
          return false;
        }

        queue.offer(current.getRight());
      }
    }

    return true;
  }
}
