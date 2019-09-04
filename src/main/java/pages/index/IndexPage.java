package pages.index;

import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class IndexPage extends BasePage {
    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public IndexPage goToPage(String url) {
        driver.get(url);
        return this;
    }
}
