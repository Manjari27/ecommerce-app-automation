package ecommerce.testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestReports {
    static ExtentReports extent;
    public static ExtentReports generateReport(){
        String path=System.getProperty("user.dir")+"\\TextExecutionResults\\EcommerceAutomationResults.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("Ecommerce Automation Results");
        reporter.config().setDocumentTitle("Results");
        extent=new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}
