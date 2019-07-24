package workerProfile;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class WorkerProfilePage extends BasePage {
    public WorkerProfilePage(WebDriver driver){
        super(driver);
    }

    private By countryBy = By.id("country");
    private By firstNameBy = By.id("firstName");
    private By middleNameBy = By.id("middleName");
    private By lastNameBy = By.id("lastName");
    private By emailBy = By.id("email");
    private By passwordBy = By.id("password");
    private By agreeBtnBy = By.cssSelector("form > div > div:nth-child(10) > div > button");
    private By verificationBy = By.cssSelector("#app > div > div > div.container > div > div.column.four > div > div > form > div > div > div > div:nth-child(1) > div > label");

    public WorkerProfilePage goToPage(String url){
        driver.get(url);
        return this;
    }

    public WorkerProfilePage selectCountry(String countryText){
        selectDropdownByText(countryBy, countryText);
        return this;
    }

    public WorkerProfilePage typeFirstName(String firstname){
        writeText(firstNameBy, firstname);
        return this;
    }

    public WorkerProfilePage typeMiddleName(String middlename){
        writeText(middleNameBy, middlename);
        return this;
    }

    public WorkerProfilePage typeLastName(String lastname){
        writeText(lastNameBy, lastname);
        return this;
    }

    public WorkerProfilePage typeEmail(){
        writeText(emailBy, generateEmail());
        return this;
    }

    public WorkerProfilePage typePassword(String password){
        writeText(passwordBy, password);
        return this;
    }

    public WorkerProfilePage clearAll(){
        selectDropdownByText(countryBy,"select country");
        clearElement(firstNameBy);
        clearElement(middleNameBy);
        clearElement(lastNameBy);
        clearElement(emailBy);
        clearElement(passwordBy);
        return this;
    }

    public void clickRegister(){
        click(agreeBtnBy);
    }

    public void assertVerification(){
        assertEquals(verificationBy, "Verification Code");
    }

    private String generateEmail(){
        Random rnd = new Random();
        int rndint = rnd.nextInt(1000000);
        return "username" + rndint + "@gmail.com";
    }
}
