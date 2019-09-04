package pages.corporationProfile;

import pages.base.BasePage;
import org.openqa.selenium.WebDriver;

public class CorporationProfilePage extends BasePage {

    public CorporationProfilePage(WebDriver driver) {
        super(driver);
    }

    public CorporationProfilePage goToPage(String url) {
        driver.get(url);
        return this;
    }
}
