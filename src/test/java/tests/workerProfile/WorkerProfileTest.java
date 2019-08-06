package tests.workerProfile;

import org.testng.Assert;
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
        wpp.verifyResponseCode(BaseUrl);
    }

    @Test(priority = 1, description = "Invalid register worker profile with wrong email.")
    public void registerWithEmptyField(Method method) {
        ExtentTestManager.startTest(method.getName(), "Invalid register worker profile with wrong email.");
        wpp.selectCountry("Canada")
                .typeFirstName("Amir Hossein")
                .typeLastName("Montazerolghaem")
                .typePassword("aS1@qwer")
                .clickRegister();
        wpp.isNotRegisterButtonDisplay();
    }

    @Test(priority = 2, description = "Register worker profile with correct info.")
    public void register(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register worker profile with correct info.");
        wpp.selectCountry("Canada")
                .typeFirstName("Amir Hossein")
                .typeLastName("Montazerolghaem")
                .typeEmail()
                .typePassword("aS1@qwer")
                .clickRegister();
        wpp.isNotRegisterButtonDisplay();
//        wpp.assertVerification();
    }

    @Test(priority = 3, description = "This test must be fail.")
    public void failTest(Method method){
        ExtentTestManager.startTest(method.getName(), "This test must be fail.");
        Assert.assertTrue(false);
    }
}
