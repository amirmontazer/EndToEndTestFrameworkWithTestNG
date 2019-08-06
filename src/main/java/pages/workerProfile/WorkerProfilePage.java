package pages.workerProfile;

import pages.base.BasePage;
import pages.base.FileHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorkerProfilePage extends BasePage {

    Map<String, By> elements;

    public WorkerProfilePage(WebDriver driver) {
        super(driver);
        init();
    }

    public void init() {
        FileHelper fileHelper = new FileHelper();
        fileHelper.openProperties("src/main/java/pages/workerProfile/resources/workerProfile.properties");

        elements = new HashMap<String, By>();
        elements.put("countryBy", By.id(fileHelper.getProperty("country")));
        elements.put("firstNameBy", By.id(fileHelper.getProperty("firstName")));
        elements.put("middleNameBy", By.id(fileHelper.getProperty("middleName")));
        elements.put("lastNameBy", By.id(fileHelper.getProperty("lastName")));
        elements.put("emailBy", By.id(fileHelper.getProperty("email")));
        elements.put("passwordBy", By.id(fileHelper.getProperty("password")));
        elements.put("agreeBtnBy", By.cssSelector(fileHelper.getProperty("agreeBtn")));
        elements.put("verificationBy", By.cssSelector(fileHelper.getProperty("verification")));
    }

    public WorkerProfilePage goToPage(String url) {
        driver.get(url);
        return this;
    }

    public WorkerProfilePage selectCountry(String countryText) {
        selectDropdownByText(elements.get("countryBy"), countryText);
        return this;
    }

    public WorkerProfilePage typeFirstName(String firstname) {
        writeText(elements.get("firstNameBy"), firstname);
        return this;
    }

    public WorkerProfilePage typeMiddleName(String middlename) {
        writeText(elements.get("middleNameBy"), middlename);
        return this;
    }

    public WorkerProfilePage typeLastName(String lastname) {
        writeText(elements.get("lastNameBy"), lastname);
        return this;
    }

    public WorkerProfilePage typeEmail() {
        writeText(elements.get("emailBy"), generateEmail());
        return this;
    }

    public WorkerProfilePage typePassword(String password) {
        writeText(elements.get("passwordBy"), password);
        return this;
    }

    public WorkerProfilePage clearAll() {
        selectDropdownByText(elements.get("countryBy"), "select country");
        clearElement(elements.get("firstNameBy"));
        clearElement(elements.get("middleNameBy"));
        clearElement(elements.get("lastNameBy"));
        clearElement(elements.get("emailBy"));
        clearElement(elements.get("passwordBy"));
        return this;
    }

    public void clickRegister() {
        click(elements.get("agreeBtnBy"));
    }

    public void assertVerification() {
        assertEquals(elements.get("verificationBy"), "Verification Code");
    }

    private String generateEmail() {
        Random rnd = new Random();
        int rndint = rnd.nextInt(1000000);
        return "username" + rndint + "@gmail.com";
    }

    public void isNotRegisterButtonDisplay() {
        isElementDisplay(elements.get("verificationBy"));
    }
}
