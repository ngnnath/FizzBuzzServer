package engine.controller;

import engine.common.FizzBuzz;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FizzBuzzControllerTest.
 */
public class FizzBuzzTest {

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzTest() {
        int limit = Integer.MAX_VALUE;

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt1LowerThenOneTest() {
        assertTrue(FizzBuzz.fizzBuzz(0, 20, 100, "str1", "str2").isEmpty());
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt2LowerThenOneTest() {
        assertTrue(FizzBuzz.fizzBuzz(20, 0, 100, "str1", "str2").isEmpty());
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt1LUpperThenLimitTest() {
        assertTrue(FizzBuzz.fizzBuzz(101, 1, 100, "str1", "str2").isEmpty());
    }


    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzInt2LUpperThenLimitTest() {
        assertTrue(FizzBuzz.fizzBuzz(2, 101, 100, "str1", "str2").isEmpty());
    }

    /**
     * Test limit.
     */
    @Test
    public void runFizzbuzzIntTest() {
        List resultExpected = Arrays.asList("1", "str1", "3", "str1", "str2", "str1", "7", "str1", "9", "str1str2");
        List<String> result = FizzBuzz.fizzBuzz(2, 5, 10, "str1", "str2");
        assertFalse(result.isEmpty());
        assertArrayEquals(resultExpected.toArray(), result.toArray());

    }
}
