package pages.base;
import org.openqa.selenium.By;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHelper {
    private  static FileHelper fileHelper_inctance = null;

    private Properties propertyFile= new Properties();
    private FileInputStream fileInputStream;

    private FileHelper() {}

    public static FileHelper getInstance()
    {
        if(fileHelper_inctance == null)
            fileHelper_inctance = new FileHelper();
        return fileHelper_inctance;
    }

    public void openProperties(String fileName){
        try {
            fileInputStream = new FileInputStream(fileName);
            propertyFile.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public By get(String locatorName){
        openProperties("resources/elements.properties");
        String locatorValue = propertyFile.getProperty(locatorName);
        String locatorType= propertyFile.getProperty(locatorName+".type");
        if(locatorType == null)
            locatorType = "id";

        By locator = null;
        switch(locatorType)
        {
            case "id":
                locator = By.id(locatorValue);
                break;
            case "name":
                locator = By.name(locatorValue);
                break;
            case "css_selector":
                locator = By.cssSelector(locatorValue);
                break;
            case "link_text":
                locator = By.linkText(locatorValue);
                break;
            case "partial_link_text":
                locator = By.partialLinkText(locatorValue);
                break;
            case "tag_name":
                locator = By.tagName(locatorValue);
                break;
            case "xpath":
                locator = By.xpath(locatorValue);
                break;
        }
        return locator;
    }

}
