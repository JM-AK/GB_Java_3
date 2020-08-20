package ru.gb.tests;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.gb.ArrayTestClass;

public class TestClass {
    private static ArrayTestClass arrayTestClass;

    @BeforeClass
    public static void init() throws Exception {
        System.out.println("init");
        arrayTestClass = new ArrayTestClass();
    }

    @AfterClass
    public static void close() {
        System.out.println("Calculator close");
    }


    @Test
    public void successSimpleTest (){
        Assert.assertArrayEquals(new int[]{1,7}, arrayTestClass.getArrAfterLastFour(new int[]{1, 2, 4, 1, 7}));
    }

    @Test (expected = RuntimeException.class)
    public void noFourSimpleTest (){
        Assert.assertArrayEquals(new int[]{1,7}, arrayTestClass.getArrAfterLastFour(new int[]{1, 2, 1, 7}));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void nullTest (){
        Assert.assertArrayEquals(new int[]{1,7}, arrayTestClass.getArrAfterLastFour(new int[]{4,4,4,4}));
    }

}
