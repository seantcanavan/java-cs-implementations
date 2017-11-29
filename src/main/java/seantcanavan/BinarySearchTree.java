package seantcanavan;


import seantcanavan.components.DoubleLinkNode;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BinarySearchTree<T extends Comparable<T>> {
    private DoubleLinkNode<T> root;
    private int order;
    public static final int PRE_ORDER = 0;
    public static final int IN_ORDER = 1;
    public static final int POST_ORDER = 2;

    public BinarySearchTree() { this.order = IN_ORDER; }

    public BinarySearchTree(int order) { this.order = order; }

    public DoubleLinkNode<T> add(T data) {
        DoubleLinkNode<T> newNode = new DoubleLinkNode<T>(data);

        if (root == null) {
            root = newNode;
            return root;
        }

        DoubleLinkNode<T> current = root;
        while (current.getData() != null) {
            if (current.getData().compareTo(data) > 0) {
                if (current.getOne() == null) {
                    current.setOne(newNode);
                    return newNode;
                } else {
                    current = current.getOne();
                }
            } else {
                if (current.getTwo() == null) {
                    current.setTwo(newNode);
                    return newNode;
                } else {
                    current = current.getTwo();
                }
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
                current = current.getOne();
            } else {
                current = current.getTwo();
            }
        }

        // not found
        return null;
    }

    public DoubleLinkNode<T> delete(T data) {
        return delete(root, root, data);
    }

    private DoubleLinkNode<T> delete(DoubleLinkNode<T> parent, DoubleLinkNode<T> current, T data) {
        if (current == null) {
            return null;
        }

        if (current.getData() == null) {
            return null;
        }

        if (current.getData().compareTo(data) > 0) {
            delete(current, current.getOne(), data);
        } else if (current.getData().compareTo(data) < 0) {
            delete(current, current.getTwo(), data);
        } else {
            // case 1 delete leaf node
            if (current.getOne() == null && current.getTwo() == null) {
                if (parent.getOne().getData().compareTo(current.getData()) == 0) {
                    parent.setOne(null);
                } else {
                    parent.setTwo(null);
                }
                return current;
            }

            // case 2 left subtree is empty
            else if (current.getOne() == null) {
                parent.setTwo(current.getTwo());
                return current;
            }

            // case 3 right subtree is empty
            else if (current.getTwo() == null) {
                parent.setOne(current.getOne());
                return current;
            }

            // case 4 neither subtree is empty
            else {
                DoubleLinkNode<T> replacementNode = findMax(current.getOne());
                current.setData(replacementNode.getData());
                current.setOne(delete(current, current.getOne(), replacementNode.getData()));
            }
        }

        //not found
        return null;
    }

    private DoubleLinkNode<T> findMax(DoubleLinkNode<T> root) {
        while (root.getTwo() != null) {
            root = root.getTwo();
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
            System.out.print(current);
            if (current.getOne() != null) {
                queue.offer(current.getOne());
            }

            if (current.getTwo() != null) {
                queue.offer(current.getTwo());
            }
        }
    }

    public void printDepthFirst(DoubleLinkNode<T> root) {
        if (root == null) {
            return;
        }

        if (order == PRE_ORDER) {
            System.out.println(root);
        }

        if (root.getOne() != null) {
            printDepthFirst(root.getOne());
        }

        if (order == IN_ORDER) {
            System.out.print(root);
        }

        if (root.getTwo() != null) {
            printDepthFirst(root.getTwo());
        }

        if (order == POST_ORDER) {
            System.out.println(root);
        }
    }
}
