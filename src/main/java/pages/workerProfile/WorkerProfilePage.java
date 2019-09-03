package pages.workerProfile;

import pages.base.BasePage;
import org.openqa.selenium.WebDriver;
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
        selectDropdownByText("workerProfile.country", countryText);
        return this;
    }

    public WorkerProfilePage fillOut(String el, String val) {
        sendKeys("workerProfile." + el, val);
        return this;
    }

    public WorkerProfilePage clearAll() {
        selectDropdownByText("workerProfile.country", "select country");
        clear("workerProfile.firstName");
        clear("workerProfile.firstName");
        clear("workerProfile.middleName");
        clear("workerProfile.lastName");
        clear("workerProfile.email");
        clear("workerProfile.password");
        return this;
    }

    public void clickButton(String el) {
        click("workerProfile." + el);
    }

    public void assertText(String el) {
        assertEquals("workerProfile." + el, "Verification Code");
    }

    public void isElementDisplayed(String el) {
        isDisplayed("workerProfile." + el);
    }

    public String generateEmail() {
        Random rnd = new Random();
        int rndint = rnd.nextInt(1000000);
        return "username" + rndint + "@gmail.com";
    }
}
