package ru.geekbrains.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculatorMassTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {20, 10, 10},
                {60, 30, 30},
                {100, 40, 40},
                {200, 20, 60},
        });
    }

    int expected;
    int x;
    int y;

    public CalculatorMassTest(int expected, int x, int y) {
        System.out.printf("%d %d %d%n", expected, x, y);
        this.expected = expected;
        this.x = x;
        this.y = y;
    }

    private static Calculator calculator;

    @BeforeClass
    public static void init() {
        calculator = new Calculator();
    }

    @Test
    public void addSuccessTest() {
        Assert.assertEquals(expected, calculator.add(x , y));
    }
}
