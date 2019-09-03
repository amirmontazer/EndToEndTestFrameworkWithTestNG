package tests.workerProfile;

import tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.workerProfile.WorkerProfilePage;
import utils.ExtentReports.ExtentTestManager;
import java.lang.reflect.Method;

public class WorkerProfileTest extends BaseTest {

    WorkerProfilePage wpp;
    String BaseUrl;

    @BeforeClass
    public void setupDriver() {
        wpp = new WorkerProfilePage(driver);
        BaseUrl = hostUrl + "#/create-worker-profile";
    }

    @BeforeMethod
    public void goToPage() {
        wpp.goToPage(BaseUrl);
    }

    @Test(priority = 0, description = "Response code should be equal to 200")
    public void checkResponseCode(Method method) {
        ExtentTestManager.startTest(method.getName(), "Response code should be equal to 200");
        wpp.assertResponseCode(BaseUrl, 200);
    }

    @Test(priority = 1, description = "Invalid register worker profile with wrong email.")
    public void registerWithEmptyField(Method method) {
        ExtentTestManager.startTest(method.getName(), "Invalid register worker profile with wrong email.");
        wpp.selectCountry("Canada")
                .fillOut("firstName", "Amir Hossein")
                .fillOut("lastName", "Montazerolghaem")
                .fillOut("password", "aS1@qwer")
                .clickButton("agreeBtn");
        wpp.isElementDisplayed("agreeBtn");
    }

    @Test(priority = 2, description = "Register worker profile with correct info.")
    public void register(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register worker profile with correct info.");
        wpp.selectCountry("Canada")
                .fillOut("firstName", "Amir Hossein")
                .fillOut("lastName", "Montazerolghaem")
                .fillOut("email", wpp.generateEmail())
                .fillOut("password", "aS1@qwer")
                .clickButton("agreeBtn");
        wpp.assertText("verification");
    }
}
