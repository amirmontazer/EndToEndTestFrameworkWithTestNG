package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public String hostUrl;

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
//        driver = new ChromeDriver();
        fileHelper fileHelper = new fileHelper();
        fileHelper.openProperties("src/main/resources/app.properties");
        hostUrl = fileHelper.getProperty("host.url");
//        Map<String, String> env = System.getenv();
        String remoteHost = System.getenv("REMOTE_HOST");
        String remotePort = System.getenv("REMOTE_PORT");
        String remoteUrl = "http://" + remoteHost + ":" + remotePort + "/wd/hub"; //"http://localhost:4444/wd/hub"
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
