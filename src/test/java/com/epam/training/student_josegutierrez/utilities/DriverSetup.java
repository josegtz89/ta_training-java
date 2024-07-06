package com.epam.training.student_josegutierrez.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Utility class for setting up the WebDriver instance.
 */
public class DriverSetup {
    /**
     * Creates and returns a new instance of ChromeDriver.
     * Make sure the ChromeDriver executable is set in the system path or set it programmatically here.
     * @return A new instance of ChromeDriver.
     */
    public static WebDriver getDriver() {
        // Optionally set the path to the chromedriver executable if not set in system properties
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        return new ChromeDriver();
    }
}