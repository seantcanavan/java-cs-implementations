package seantcanavan.components;

public class HashValue {
    private String key;
    private String value;

    public HashValue(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("no null values allowed");
        }

        if (key.isEmpty() || value.isEmpty()) {
            throw new IllegalArgumentException("no empty values allowed");
        }

        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "key: " +
                key +
                "  " +
                "value: " +
                value;
    }
}