package seantcanavan.components;

public class SingleLinkNode<T extends Comparable<T>> extends Node<T> {
    private SingleLinkNode<T> one;

    public SingleLinkNode(T data) { super(data); }

    public SingleLinkNode<T> getOne() { return one; }

    public void setOne(SingleLinkNode<T> one) { this.one = one; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        sb.append("| ");
        if (data != null) {
            sb.append(data.toString());
        }
        sb.append(" |");

        if (one != null) {
            sb.append(" => ");
            sb.append("[");
            sb.append(one.toString());
            sb.append("]");
        }

        return sb.toString();
    }
}
