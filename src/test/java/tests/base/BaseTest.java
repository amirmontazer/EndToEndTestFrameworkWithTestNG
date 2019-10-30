package tests.base;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReports.ExtentManager;
import utils.ExtentReports.ExtentTestManager;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public String hostUrl;
    public String baseUrl;

    public ExtentTest parent;
    public ExtentTest child;

    private Map<Long, ExtentTest> parentContext = new HashMap<Long, ExtentTest>();
    private int testCount = 0, successCount = 0, failCount = 0, skipCount = 0;

    @BeforeClass
    public synchronized void beforeClass(ITestContext caller) throws MalformedURLException {
        setHostUrl();
        driver = new RemoteWebDriver(new URL(getRemoteWebDriverUrl()), initCapabilities());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        String className = this.getClass().getSimpleName();
        parent = ExtentTestManager
                .startTest(className.substring(0, className.length() - 4), "")
                .assignCategory(Thread.currentThread().getName());
        parentContext.put(Thread.currentThread().getId(), parent);
    }

    @AfterClass
    public void teardown() {
        LogStatus logStatus = LogStatus.PASS;
        if (failCount > 0)
            logStatus = LogStatus.FAIL;
        else if (skipCount > 0)
            logStatus = LogStatus.SKIP;
        String description = "Total tests run: " + testCount + ", Passes: " + successCount + ", Failures: " + failCount + ", Skips: " + skipCount;
        parentContext.get(Thread.currentThread().getId()).log(logStatus, description);

        ExtentTestManager.endTest();
        ExtentManager.getReporter().flush();

        if (null != driver) {
            driver.close();
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        testCount++;
        child = ExtentTestManager.startTest(method.getName(), method.getAnnotation(Test.class).description())
                .assignCategory(Thread.currentThread().getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.isSuccess()) {
            successCount++;
        } else if (result.getStatus() == ITestResult.FAILURE) {
            failCount++;
        } else if (result.getStatus() == ITestResult.SKIP) {
            skipCount++;
        }

        parentContext.get(Thread.currentThread().getId()).appendChild(child);
        ExtentManager.getReporter().flush();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private DesiredCapabilities initCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setPlatform(Platform.LINUX);
        capabilities.setBrowserName("chrome");
        return capabilities;
    }

    private String getRemoteWebDriverUrl() {
        //        Map<String, String> env = System.getenv();
        String remoteHost = getenv("REMOTE_HOST");
        String remotePort = getenv("REMOTE_PORT");
        return "http://" + remoteHost + ":" + remotePort + "/wd/hub"; //"http://localhost:4444/wd/hub"
    }

    private void setHostUrl() {
        hostUrl = getenv("HOST_URL");
    }

    private String getenv(String name){
        return System.getenv(name).trim();
    }
}
