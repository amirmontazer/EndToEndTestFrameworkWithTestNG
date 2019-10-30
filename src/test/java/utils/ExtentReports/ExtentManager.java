package utils.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports("ExtentReports/" + getCurrentDateTime() + ".html", true);
            extent.loadConfig(new File("resources/extent-config.xml"));
        }
        return extent;
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yyMMdd]-HH.mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
