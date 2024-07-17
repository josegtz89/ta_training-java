package com.epam.training.student_josegutierrez.utilities;

import com.epam.training.student_josegutierrez.driver.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public abstract class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        String browserType = ConfigReader.getProperty("browser.type");
        driver = DriverSetup.getDriver(browserType);
        driver.manage().window().maximize();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}