package com.epam.training.student_josegutierrez.tests.Framework_Retest;


import com.epam.training.student_josegutierrez.driver.DriverSetup;
import com.epam.training.student_josegutierrez.services.ConfigReader;
import com.epam.training.student_josegutierrez.utilities.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;


/**
 * This abstract class serves as a base for all test classes.
 */
@Listeners({TestListener.class})
public abstract class BaseTest {
    protected static WebDriver driver;

    /**
     * Initializes the WebDriver before any tests are run.
     */
    @BeforeClass
    public static void setUp() {
        driver = DriverSetup.getDriver(ConfigReader.getProperty("browser.type"));
        if (driver == null) {
            throw new IllegalStateException("Driver did not initialize properly.");
        }
        driver.manage().window().maximize();
    }

    /**
     * Cleans up the WebDriver after all tests in the class have completed.
     */
    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Provides access to the WebDriver instance for the test scenarios.
     * @return The WebDriver instance being used by the tests.
     */
    public static WebDriver getDriver() {
        return driver;
    }
}