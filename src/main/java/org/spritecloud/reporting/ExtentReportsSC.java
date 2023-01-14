package org.spritecloud.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportsSC {
    private static String FILE_PATH = "testresults/reports/SC_TestReport_";
    private static String FILE_EXTENSION = "html";
    static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
    static String filename = FILE_PATH + dateFormat.format(new Date()) + "." + FILE_EXTENSION;
    public static ExtentReports getReportObject() {
       // String path = System.getProperty("user.dir") + "\\reports\\report.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filename);
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setReportName("Sprite Cloud UI and API Automation Test Results");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}
