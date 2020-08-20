package ru.gb.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.gb.ArrayTestClass;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ArrayMassTest {
    private static ArrayTestClass arrayTestClass;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {true, new int[]{1,1,1,4,4}},
                {true, new int[]{1,1,1,1,1}},
                {true, new int[]{4,4,4}},
                {true, new int[]{1,2,1,4,4}},
        });
    }

    boolean expected;
    int[] array;

    public ArrayMassTest(boolean expected, int[] array) {
        this.expected = expected;
        this.array = array;
    }

    @BeforeClass
    public static void init() {
        arrayTestClass = new ArrayTestClass();
    }

    @Test
    public void successTest(){
        Assert.assertEquals(expected, arrayTestClass.checkContainNumsOneAndFour(array));
    }

}
