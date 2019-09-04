package tests.corporationProfile;

import tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.corporationProfile.CorporationProfilePage;
import utils.ExtentReports.ExtentTestManager;
import java.lang.reflect.Method;

public class CorporationProfileTest extends BaseTest {

    CorporationProfilePage cpp;
    String BaseUrl;

    @BeforeClass
    public void setupDriver() {
        cpp = new CorporationProfilePage(driver);
        BaseUrl = hostUrl + "#/create-corporate-profile";
    }

    @BeforeMethod
    public void goToPage() {
        cpp.goToPage(BaseUrl);
    }

    @Test(priority = 0, description = "CorporationPage Response code should be equal to 200")
    public void checkCorporationPageResponseCode(Method method) {
        ExtentTestManager.startTest(method.getName(), "CorporationPage Response code should be equal to 200");
        cpp.assertResponseCode(BaseUrl, 200);
    }

    @Test(priority = 1, description = "CorporationPage header should be shown properly")
    public void checkCorporationPageHeader(Method method) {
        ExtentTestManager.startTest(method.getName(), "CorporationPage header should be shown properly");
        cpp.assertEquals("corporationProfile.pageTitle", "Corporation Profile Registration");
    }

    @Test(priority = 2, description = "CorporationPage Cancel button should redirect to the main page")
    public void checkCorporationPageCancelButton(Method method) {
        ExtentTestManager.startTest(method.getName(), "CorporationPage Cancel button should redirect to the main page");
        cpp.click("corporationProfile.cancelButton");
        cpp.isDisplayed("index.joinNowButton");
    }
}
