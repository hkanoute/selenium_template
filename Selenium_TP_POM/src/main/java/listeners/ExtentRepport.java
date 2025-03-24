package listeners;

import BaseTest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ConfigReader;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentRepport implements ITestListener {

    public static ExtentReports extent;
    protected ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentRepport.extent.createTest(result.getMethod().getMethodName(),"this is a sample test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("result.getTestName()");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        File screenshot = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);

        String screenshotDir = System.getProperty("user.dir") + ConfigReader.getProperty("SCREEN_SHOT_DIR");

        File destination = new File(screenshotDir + "screenshot_" + timestamp + ".png");

        try {

            FileUtils.copyFile(screenshot, destination);

            test.addScreenCaptureFromPath(ConfigReader.getProperty("SCREEN_SHOT_DIR") + "screenshot_" + timestamp + ".png" );

        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement de la capture d'écran", e);
        }
        test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + ConfigReader.getProperty("REPPORT_DIR");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester","QA Engineer");
        extent.setSystemInfo("OS",System.getProperty("os.name"));
        extent.setSystemInfo("Java Version",System.getProperty("java.version"));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

}


