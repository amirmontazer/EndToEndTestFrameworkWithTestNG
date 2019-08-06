package utils.ExtentReports;

import com.relevantcodes.extentreports.ExtentReports;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.
public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            //Set HTML reporting file location
//            String workingDir = System.getProperty("user.dir");
            extent = new ExtentReports("ExtentReports/" + getCurrentDateTime() + ".html", true);
        }
        return extent;
    }

    private static String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("[yyMMdd]-HH.mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
