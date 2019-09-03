package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasePage {
    private static final int TIMEOUT = 15;
    private static final int POLLING = 100;

    public WebDriver driver;
    public WebDriverWait wait;
    public FileHelper elements;

    public BasePage(WebDriver driver) {
        initialPage(driver);
    }

    private void initialPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POLLING);
        elements = FileHelper.getInstance();
    }

    private By getElementBy(String el){
        return elements.get(el);
    }

    private WebElement findElement(String el){
        return driver.findElement(getElementBy(el));
    }

    public void waitVisibility(String el) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getElementBy(el)));
    }

    public void click(String el) {
        waitVisibility(el);
        findElement(el).click();
    }

    public void sendKeys(String el, String text) {
        waitVisibility(el);
        WebElement webEl = findElement(el);
        webEl.clear();
        webEl.sendKeys(text);
    }

    public String getText(String el) {
        waitVisibility(el);
        return findElement(el).getText();
    }

    public void assertEquals(String el, String expectedText) {
        waitVisibility(el);
        Assert.assertEquals(getText(el), expectedText);
    }

    public void selectDropdownByText(String el, String expectedText) {
        waitVisibility(el);
        Select drp = new Select(findElement(el));
        drp.selectByVisibleText(expectedText);
    }

    public void selectDropdownByValue(String el, String expectedValue) {
        waitVisibility(el);
        Select drp = new Select(findElement(el));
        drp.selectByValue(expectedValue);
    }

    public void selectFromAutocomplete(String el, String text, int millis) throws InterruptedException {
        waitVisibility(el);
        WebElement webEl = findElement(el);
        webEl.clear();
        webEl.sendKeys(text);
        Thread.sleep(millis);
        webEl.sendKeys(Keys.ARROW_DOWN);
        webEl.sendKeys(Keys.ENTER);
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
        }
    }

    public void clear(String el) {
        waitVisibility(el);
        findElement(el).clear();
    }

    public void assertResponseCode(String url, int expectedResponseCode) {
        HttpURLConnection huc = null;
        int respCode = 200;
        try {
            huc = (HttpURLConnection) (new URL(url).openConnection());
            huc.setRequestMethod("HEAD");
            huc.connect();
            respCode = huc.getResponseCode();
        } catch (Exception e) {
            respCode = 1000;
        } finally {
            huc.disconnect();
        }
        Assert.assertTrue(respCode == expectedResponseCode, url + " is a broken link. \r\n");
    }

    public void isDisplayed(String el) {
        try {
            Assert.assertTrue(findElement(el).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(false);
        }
    }

    public void isNotDisplayed(String el) {
        try {
            Assert.assertFalse(findElement(el).isDisplayed());
        } catch (Exception ex) {
            Assert.assertTrue(true);
        }
    }
}
