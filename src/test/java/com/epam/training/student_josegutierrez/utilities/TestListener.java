package com.epam.training.student_josegutierrez.utilities;

import com.epam.training.student_josegutierrez.tests.Framework_Task.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * A TestNG listener class that extends ITestListener to perform actions based on test execution events.
 */
public class TestListener implements ITestListener {

    /**
     * Invoked each time a test fails.
     * @param result Provides information about the test method that failed.
     */
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = BaseTest.getDriver();
        if (driver != null) {
            ScreenshotUtility.takeScreenshot(driver, result.getMethod().getMethodName());
        } else {
            System.out.println("Driver is null, cannot take screenshot");
        }
    }

    /**
     * Invoked before the first test method in the current class is invoked.
     * @param context Provides context for the test being executed.
     */
    @Override
    public void onStart(ITestContext context) {}

    /**
     * Invoked after all the test methods in the current class have been executed.
     * @param context Provides context for the test being executed.
     */
    @Override
    public void onFinish(ITestContext context) {}

    /**
     * Invoked each time a test method is about to start.
     * @param result Provides information about the test method that is about to be executed.
     */
    @Override
    public void onTestStart(ITestResult result) {}

    /**
     * Invoked each time a test method succeeds.
     * @param result Provides information about the test method that succeeded.
     */
    @Override
    public void onTestSuccess(ITestResult result) {}

    /**
     * Invoked each time a test method is skipped.
     * @param result Provides information about the test method that was skipped.
     */
    @Override
    public void onTestSkipped(ITestResult result) {}

    /**
     * Invoked each time a test method fails but is within the success percentage specified.
     * @param result Provides information about the test method that failed but was within the success percentage.
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}