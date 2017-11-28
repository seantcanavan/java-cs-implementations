package com.seantcanavan.javacsimplementations.main.domain.components;

public class DoubleLinkNode<T extends Comparable<T>> extends Node<T> {
    private DoubleLinkNode<T> one;
    private DoubleLinkNode<T> two;

    public DoubleLinkNode(T data) { super(data); }

    public DoubleLinkNode(T data, DoubleLinkNode<T> one, DoubleLinkNode<T> two) {
        super(data);
        this.one = one;
        this.two = two;
    }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public DoubleLinkNode<T> getOne() { return one; }

    public void setOne(DoubleLinkNode<T> one) { this.one = one; }

    public DoubleLinkNode<T> getTwo() { return two; }

    public void setTwo(DoubleLinkNode<T> two) { this.two = two; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (one != null) {
            sb.append("[");
            sb.append(one.toString());
            sb.append("]");
            sb.append(" <= ");
        }

        sb.append("| ");
        if (data != null) {
            sb.append(data.toString());
        }
        sb.append(" |");

        if (two != null) {
            sb.append(" => ");
            sb.append("[");
            sb.append(two.toString());
            sb.append("]");
        }

        return sb.toString();
    }
}
