package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class fileHelper {
    private Properties appProps;

    public fileHelper() {
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
