package com.epam.training.student_josegutierrez.utilities;


import com.epam.training.student_josegutierrez.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * This abstract class serves as a base for all test classes.
 */
public abstract class BaseTest {
    protected static WebDriver driver;

    /**
     * Initializes the WebDriver before any tests are run.
     */
    @BeforeClass
    public static void setUp() {
        driver = DriverSetup.getDriver(ConfigReader.getProperty("browser.type"));
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