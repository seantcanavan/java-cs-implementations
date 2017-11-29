package seantcanavan;

import static org.fest.assertions.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreeterTest {

    private Greeter greeter = new Greeter();

    private static final Logger LOG = LoggerFactory.getLogger(GreeterTest.class);

    @BeforeMethod(alwaysRun = true)
    public void reset() {
        greeter = new Greeter();
    }

    @Test
    public void testPrint() {
        String result = greeter.sayHello();
        assertThat(result).isEqualTo("Hello world!");
    }
}
