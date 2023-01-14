package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.spritecloud.reporting.ExtentReportsSC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {
    ExtentReports extent= ExtentReportsSC.getReportObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result){
        test= extent.createTest(result.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult result){
        test.log(Status.PASS,"Test Passed");
    }
    @Override
    public void onTestFailure(ITestResult result){
        test.fail(result.getThrowable());
    }
    @Override
    public void onTestSkipped(ITestResult result){
        test.log(Status.SKIP,"Test Skipped");
    }
    @Override
    public void onFinish(ITestContext context){
        extent.flush();
    }


}
