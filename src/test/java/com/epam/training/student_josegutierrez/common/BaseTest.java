package com.epam.training.student_josegutierrez.common;

import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.openqa.selenium.WebDriver;
import org.junit.BeforeClass;
import org.junit.AfterClass;

/**
 * Base test class that setups and tears down WebDriver instance.
 */

public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = DriverSetup.getDriver();
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
