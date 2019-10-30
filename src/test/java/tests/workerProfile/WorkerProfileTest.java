package tests.workerProfile;

import tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.workerProfile.WorkerProfilePage;

public class WorkerProfileTest extends BaseTest {

    WorkerProfilePage wpp;

    @BeforeClass
    public void initClass() {
        wpp = new WorkerProfilePage(driver);
        baseUrl = hostUrl + "#/create-worker-profile";
    }

    @BeforeMethod
    public void initMethod() {
        wpp.goToPage(baseUrl);
    }

    @Test(priority = 0, description = "Response code should be equal to 200")
    public void checkResponseCode() {
        wpp.assertResponseCode(baseUrl, 200);
    }

    @Test(priority = 1, description = "Invalid register worker profile with wrong email")
    public void registerWithEmptyField() {
        wpp.selectCountry("Canada")
                .fillOut("firstName", "Amir Hossein")
                .fillOut("lastName", "Montazerolghaem")
                .fillOut("password", "aS1@qwer")
                .clickButton("agreeBtn");
        wpp.isElementDisplayed("agreeBtn");
    }

    @Test(priority = 2, description = "Register worker profile with correct info")
    public void register() {
        wpp.selectCountry("Canada")
                .fillOut("firstName", "Amir Hossein")
                .fillOut("lastName", "Montazerolghaem")
                .fillOut("email", wpp.generateEmail())
                .fillOut("password", "aS1@qwer")
                .clickButton("agreeBtn");
        wpp.assertText("verification");
    }
}
