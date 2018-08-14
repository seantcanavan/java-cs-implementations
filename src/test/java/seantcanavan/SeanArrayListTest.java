package seantcanavan;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.fest.assertions.api.Assertions.assertThat;

public class SeanArrayListTest {

    private static class SeanElement implements Comparable<SeanElement> {

        private int data;

        public SeanElement() { }

        public SeanElement(int data) { this.data = data; }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public static SeanElement buildRandom() {
            return new SeanElement(new SecureRandom().nextInt(10000));
        }

        @Override
        public int compareTo(SeanElement seanElement) {
            return this.data - seanElement.getData();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SeanElement that = (SeanElement) o;
            return data == that.data;
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }

	@Test
	public void shouldInitializeArrayList() {
        // empty constructor
        SeanArrayList<SeanElement> sal = new SeanArrayList<>();
        assertThat(sal.getAllocatedCapacity()).isEqualTo(SeanArrayList.DEFAULT_CAPACITY);

        // initial value constructor
        int initialCapacity = 20;
        sal = new SeanArrayList<>(initialCapacity);
        assertThat(sal.getAllocatedCapacity()).isEqualTo(initialCapacity);

        // initialize with another list
        int initialElementCount = 27;
        int reallocationCount = 27 / SeanArrayList.DEFAULT_CAPACITY;
        List<SeanElement> initialElements = new ArrayList<>();
        for (int i = 0; i < initialElementCount; i++) {
            initialElements.add(SeanElement.buildRandom());
        }

        sal = new SeanArrayList<>(initialElements);
        assertThat(sal.getSize()).isEqualTo(initialElementCount);
        assertThat(sal.getAllocatedCapacity()).isEqualTo(SeanArrayList.DEFAULT_CAPACITY * 2 * reallocationCount);
        assertThat(sal.getAllocatedCapacity()).isGreaterThan(sal.getSize());

        for (int i = 0; i < initialElementCount; i++) {
            assertThat(initialElements.get(i)).isEqualTo(sal.get(i));
        }
    }
}
