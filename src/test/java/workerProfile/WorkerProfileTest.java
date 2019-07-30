package workerProfile;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class WorkerProfileTest extends BaseTest {

    WorkerProfilePage wpp;
    String BaseUrl = "http://192.168.1.35/#/create-worker-profile";

    @BeforeClass
    public void setup1(){

        wpp = new WorkerProfilePage(driver);
    }

    @BeforeMethod
    public void goToPage(){

        wpp.goToPage(BaseUrl);
    }

    @Test (priority = 0)
    public void checkResponseCode(){
       wpp.verifyResponseCode(BaseUrl);
    }

    @Test (priority = 1)
    public void registerWithEmptyField(){
        wpp.clickRegister();
//        wpp.sleep(3000);
    }

    @Test (priority = 2)
    public void register(){
        wpp.selectCountry("Canada")
                .typeFirstName("Amir Hossein")
                .typeLastName("Montazerolghaem")
                .typeEmail()
                .typePassword("aS1@qwer");
//                .clickRegister();

        wpp.sleep(3000);
        wpp.clickRegister();
        wpp.sleep(3000);

        wpp.assertVerification();
    }
}
