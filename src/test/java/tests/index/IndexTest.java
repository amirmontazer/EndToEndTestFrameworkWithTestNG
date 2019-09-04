package tests.index;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.index.IndexPage;
import tests.base.BaseTest;
import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;

public class IndexTest extends BaseTest {

    IndexPage indexPage;
    String BaseUrl;

    @BeforeClass
    public void setupDriver() {
        indexPage = new IndexPage(driver);
        BaseUrl = hostUrl + "#/";
    }

    @BeforeMethod
    public void goToPage() {
        indexPage.goToPage(BaseUrl);
    }

    @Test(priority = 0, description = "Index Page Response code should be equal to 200")
    public void checkIndexPageResponseCode(Method method) {
        ExtentTestManager.startTest(method.getName(), "Index Page Response code should be equal to 200");
        indexPage.assertResponseCode(BaseUrl, 200);
    }

    @Test(priority = 1, description = "IndexPage header should be shown properly")
    public void checkIndexPageHeader(Method method) {
        ExtentTestManager.startTest(method.getName(), "IndexPage header should be shown properly");
        indexPage.assertEquals("index.pageTitle", "Work with Us");
    }
}
