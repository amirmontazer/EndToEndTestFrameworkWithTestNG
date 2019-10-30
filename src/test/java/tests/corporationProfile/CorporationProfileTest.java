package tests.corporationProfile;

import tests.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.corporationProfile.CorporationProfilePage;

public class CorporationProfileTest extends BaseTest {

    CorporationProfilePage cpp;

    @BeforeClass
    public void initClass() {
        cpp = new CorporationProfilePage(driver);
        baseUrl = hostUrl + "#/create-corporate-profile";
    }

    @BeforeMethod
    public void initMethod() {
        cpp.goToPage(baseUrl);
    }

    @Test(priority = 0, description = "CorporationPage Response code should be equal to 200")
    public void checkCorporationPageResponseCode() {
        cpp.assertResponseCode(baseUrl, 200);
    }

    @Test(priority = 1, description = "CorporationPage header should be shown properly")
    public void checkCorporationPageHeader() {
        cpp.assertEquals("corporationProfile.pageTitle", "Corporation Profile Registration");
    }

    @Test(priority = 2, description = "CorporationPage Cancel button should redirect to the main page")
    public void checkCorporationPageCancelButton() {
        cpp.click("corporationProfile.cancelButton");
        cpp.isDisplayed("index.joinNowButton");
    }
}
