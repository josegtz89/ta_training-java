package com.epam.training.student_josegutierrez.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * Utility class for setting up the WebDriver instance using WebDriverManager.
 */
public class DriverSetup {
    /**
     * Returns a WebDriver instance based on the specified browser type.
     * @param browserType The type of the browser (e.g., "chrome", "firefox", "edge").
     * @return A WebDriver instance.
     */
    public static WebDriver getDriver(String browserType) {
        WebDriver driver;
        switch (browserType.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        return driver;
    }
}