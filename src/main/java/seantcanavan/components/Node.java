package seantcanavan.components;

public class Node<T extends Comparable<T>> {
    protected T data;

    public Node(T data) { this.data = data; }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }
}
