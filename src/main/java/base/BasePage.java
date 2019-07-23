package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {
    private static final int TIMEOUT = 15;
    private static final int POLLING = 100;

    public WebDriver driver;
    public WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,TIMEOUT, POLLING);
//        PageFactory.initElements(driver, this);
    }

    //Wait Wrapper Method
    protected void waitVisibility(By elementBy) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    //Click Method
    public void click(By elementBy) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).click();
    }

    //Write Text
    public void writeText(By elementBy, String text) {
        waitVisibility(elementBy);
        driver.findElement(elementBy).sendKeys(text);
    }

    //Read Text
    public String readText(By elementBy) {
        waitVisibility(elementBy);
        return driver.findElement(elementBy).getText();
    }

    //Assert
    public void assertEquals(By elementBy, String expectedText) {
        waitVisibility(elementBy);
        Assert.assertEquals(readText(elementBy), expectedText);
    }

    //Select drop-down by text
    public void selectDropdownByText(By elementBy, String expectedText){
        waitVisibility(elementBy);
        Select drp = new Select(driver.findElement(elementBy));
        drp.selectByVisibleText(expectedText);
    }

    //Select drop-down by value
    public void selectDropdownByValue(By elementBy, String expectedValue){
        waitVisibility(elementBy);
        Select drp = new Select(driver.findElement(elementBy));
        drp.selectByValue(expectedValue);
    }

    //Select from autocomplete
    public void selectFromAutocomplete(By elementBy, String text, int millis ) throws InterruptedException{
        waitVisibility(elementBy);
        WebElement el = driver.findElement(elementBy);
        el.clear();
        el.sendKeys(text);
        Thread.sleep(millis);
        el.sendKeys(Keys.ARROW_DOWN);
        el.sendKeys(Keys.ENTER);
    }
}
