package com.vasya;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class htmlTagsTest {
    private static WebDriver driver;
    private static String url;

    @BeforeClass
    public static void beforeMethod() {
        driver = new ChromeDriver();
        url = "https://stackoverflow.com/";
        driver.get(url);
    }

    @Test
    public void testMethod () {
        int scriptListSize = driver.findElements(By.xpath("//script")).size();
        assertTrue(scriptListSize == 1); //проверка 1 JS на сайте
    }

    @Test
    public void testMethodJSFromFiles () {
        int scriptListSize = driver.findElements(By.xpath("//script[not@src]")).size();
        assertTrue(scriptListSize == 0); //проверка прогружены ли JS из файлов
    }

    @Test
    public void testMethod1CSS () {
        int cssListSize = driver.findElements(By.xpath("//link[@rel ='stylesheet']")).size();
        assertTrue(cssListSize == 1); //проверка 1 CSS на сайте
    }

    @Test
    public void testMethodCSSFromFiles () {
        int cssListSize = driver.findElements(By.xpath("//style")).size();
        assertTrue(cssListSize == 0); //проверка прогружены ли CSS из файлов
    }

    @Test
    public void testMethodCheckH1 () {
        int h1ListSize = driver.findElements(By.xpath("//h1")).size();
        assertTrue(h1ListSize <= 1); //кол-во h1 на сайте
    }

    @Test
    public void testMethodUniqueID () {
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id]"));
        List<String> idsBefore = new ArrayList<String>();
        for (WebElement el: elements) {
            idsBefore.add(el.getAttribute("id"));
        }
        Set<String> idsAfter = new HashSet<String>();
        idsAfter.addAll(idsBefore);
        assertTrue( idsAfter.size() == idsBefore.size()); //уникальные id
    }

    @AfterClass
    public static void afterMethod () {
        driver.quit();
    }

}
