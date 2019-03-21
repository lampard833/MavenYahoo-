package com.vasya;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class YahooSearchTest {
    private WebDriver driver;
    private String url;
    private String cssSelectorSearch;
    private String searchPhrase;
    private String cssSelectorSearchResult;
    private int pages;

    @Before
    public void beforeMethod () {
        driver = new ChromeDriver();
        url = "https://www.yahoo.com/";
        cssSelectorSearch = "input[id = 'uh-search-box']";
        searchPhrase = "Google knows everything";
        cssSelectorSearchResult = "a.lh-24";
        pages = 2;
        driver.get(url);

    }

    @Test
    public void testMethodYahooSearchTest () {
        driver.findElement(By.cssSelector(cssSelectorSearch)).sendKeys(searchPhrase);
        driver.findElement(By.cssSelector(cssSelectorSearch)).sendKeys(Keys.ENTER);

        String [] words = splitStringToWords(searchPhrase);
        List<String> searchResult = getYahooSearchResult(pages);
        for (String el: searchResult) {
            //System.out.println(searchResult.size());
            System.out.println(el);

            /*for (int i = 0; i < words.length; i++) {
                assertTrue("Error: \n" + el.toLowerCase() + "' doesn't contain\n" + words[i].toLowerCase(), el.toLowerCase().contains(words[i].toLowerCase()));
            }*/
            //System.out.println(el.findElement(By.cssSelector("cssSelectorSearchResult")).getText());
            //assertTrue(el.getText().toLowerCase().contains(searchPhrase.toLowerCase()));
        }
    }

    @After
    public void afterMethod () {
        driver.quit();
    }

    private ArrayList <String> getYahooSearchResult(int pages) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i <= pages - 1; i++) {
            List<WebElement> elList = driver.findElements(By.cssSelector(cssSelectorSearchResult));
            for (WebElement el: elList) {
                result.add(el.getText());
            }
            if (i == pages - 1) {
                return result;
            }
            driver.findElement(By.xpath("//div/div/div/div/div/div/ol/li/div/div/a[@href][" + (i + 1) + "]")).click();
        }
        return result;
    }

    private String[] splitStringToWords (String string) {
        return string.split("\\s+");

    }

}

