package ru.levelp.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testSum() {
        int actual = new Main().sum(1, 1);
        int expected = 2;

        assertEquals(expected, actual);
    }
}
