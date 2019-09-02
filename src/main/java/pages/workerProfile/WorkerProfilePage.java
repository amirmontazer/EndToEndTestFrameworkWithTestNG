package pages.workerProfile;

import pages.base.BasePage;
import pages.base.FileHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WorkerProfilePage extends BasePage {

    public WorkerProfilePage(WebDriver driver) {
        super(driver);
    }

    public WorkerProfilePage goToPage(String url) {
        driver.get(url);
        return this;
    }

    public WorkerProfilePage selectCountry(String countryText) {
        selectDropdownByText(element.get("workerProfile.country"), countryText);
        return this;
    }

    public WorkerProfilePage typeFirstName(String firstname) {
        writeText(element.get("workerProfile.firstName"), firstname);
        return this;
    }

    public WorkerProfilePage typeMiddleName(String middlename) {
        writeText(element.get("workerProfile.middleName"), middlename);
        return this;
    }

    public WorkerProfilePage typeLastName(String lastname) {
        writeText(element.get("workerProfile.lastName"), lastname);
        return this;
    }

    public WorkerProfilePage typeEmail() {
        writeText(element.get("workerProfile.email"), generateEmail());
        return this;
    }

    public WorkerProfilePage typePassword(String password) {
        writeText(element.get("workerProfile.password"), password);
        return this;
    }

    public WorkerProfilePage clearAll() {
        selectDropdownByText(element.get("workerProfile.country"), "select country");
        clearElement(element.get("workerProfile.firstName"));
        clearElement(element.get("workerProfile.middleName"));
        clearElement(element.get("workerProfile.lastName"));
        clearElement(element.get("workerProfile.email"));
        clearElement(element.get("workerProfile.password"));
        return this;
    }

    public void clickRegister() {
        click(element.get("workerProfile.agreeBtn"));
    }

    public void assertVerification() {
        assertEquals(element.get("workerProfile.verification"), "Verification Code");
    }

    private String generateEmail() {
        Random rnd = new Random();
        int rndint = rnd.nextInt(1000000);
        return "username" + rndint + "@gmail.com";
    }

    public void isRegisterButtonDisplay() {
        isElementDisplay(element.get("workerProfile.agreeBtn"));
    }
}
