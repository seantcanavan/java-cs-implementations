package seantcanavan.components;

import java.security.SecureRandom;
import java.util.Objects;

public class DataElement implements Comparable<DataElement> {

    private int data;

    public DataElement() {}

    public DataElement(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public static DataElement getRandom() {
        return new DataElement(new SecureRandom().nextInt(10000));
    }

    @Override
    public int compareTo(DataElement dataElement) {
        return this.data - dataElement.getData();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataElement that = (DataElement) o;
        return data == that.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}