package tests.index;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.index.IndexPage;
import tests.base.BaseTest;

public class IndexTest extends BaseTest {

    IndexPage indexPage;

    @BeforeClass
    public void initClass() {
        indexPage = new IndexPage(driver);
        baseUrl = hostUrl + "#/";
    }

    @BeforeMethod
    public void initMethod() {
        indexPage.goToPage(baseUrl);
    }

    @Test(priority = 0, description = "Index Page Response code should be equal to 200")
    public void checkIndexPageResponseCode() {
        indexPage.assertResponseCode(baseUrl, 200);
    }

    @Test(priority = 1, description = "IndexPage header should be shown properly")
    public void checkIndexPageHeader() {
        indexPage.assertEquals("index.pageTitle", "Work with Us");
    }
}
