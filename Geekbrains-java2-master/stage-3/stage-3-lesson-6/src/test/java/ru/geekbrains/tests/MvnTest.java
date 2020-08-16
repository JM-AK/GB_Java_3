package ru.geekbrains.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MvnTest {

    @Test
    public void testSearchLine() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://mvnrepository.com");
        WebElement searchLine = driver.findElement((By.cssSelector(".textfield")));
        searchLine.sendKeys("selenium");
        searchLine.submit();
        List<WebElement> libBlocks = driver.findElements(By.cssSelector(".im"));
        System.out.println(libBlocks.size());
        Assert.assertEquals(10, libBlocks.size());
        driver.quit();
    }

}
