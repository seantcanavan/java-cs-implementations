package seantcanavan.components;

public class SingleLinkNode<T extends Comparable<T>> extends Node<T> {
    private SingleLinkNode<T> next;

    public SingleLinkNode(T data) { super(data); }

    public SingleLinkNode<T> getNext() { return next; }

    public void setNext(SingleLinkNode<T> next) { this.next = next; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("| ");
        if (data != null) {
            sb.append(data.toString());
        }
        sb.append(" |");

        if (next != null) {
            sb.append(" => ");
            sb.append("[");
            sb.append(next.toString());
            sb.append("]");
        }

        return sb.toString();
    }
}
