import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import engine.services.FizzBuzz;
import engine.errors.FizzbuzzParamException;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * FizzBuzzControllerTest.
 */
public class FizzBuzzTest {

    /**
     * FIZZBUZZ.
     */
    private static FizzBuzz FIZZBUZZ;


    @BeforeAll
    public static void init() {
        final SimpleMeterRegistry meterRegistry = new SimpleMeterRegistry();
        FIZZBUZZ = new FizzBuzz(meterRegistry);
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzlimitIntTest() {
        int limit = Integer.MAX_VALUE + 1;
        assertThrows(FizzbuzzParamException.class, () -> FIZZBUZZ.fizzBuzz(1, 20, limit, "str1", "str2"));

    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt1LowerThenOneTest() {
        assertThrows(FizzbuzzParamException.class, () -> FIZZBUZZ.fizzBuzz(0, 20, 100, "str1", "str2"));
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt2LowerThenOneTest() {
        assertThrows(FizzbuzzParamException.class, () -> FIZZBUZZ.fizzBuzz(20, 0, 100, "str1", "str2"));
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt1LUpperThenLimitTest() {
        assertThrows(FizzbuzzParamException.class, () -> FIZZBUZZ.fizzBuzz(101, 1, 100, "str1", "str2"));
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt2LUpperThenLimitTest() {
        assertThrows(FizzbuzzParamException.class, () -> FIZZBUZZ.fizzBuzz(2, 101, 100, "str1", "str2"));
    }


    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzIntTest() {
        List resultExpected = Arrays.asList("1", "str1", "3", "str1", "str2", "str1", "7", "str1", "9", "str1str2");
        List<String> result = FIZZBUZZ.fizzBuzz(2, 5, 10, "str1", "str2");
        assertFalse(result.isEmpty());
        assertArrayEquals(resultExpected.toArray(), result.toArray());

    }
}
