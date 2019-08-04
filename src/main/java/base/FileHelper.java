package base;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileHelper {
    private Properties appProps;
    private Document xmlDocument;

    public FileHelper() {
    }

    public void openProperties(String pathName) {
        appProps = new Properties();
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(pathName);
            appProps.load(fileInputStream);
//            appProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        if (appProps != null)
            return appProps.getProperty(key);
        else
            return "";
    }
}
