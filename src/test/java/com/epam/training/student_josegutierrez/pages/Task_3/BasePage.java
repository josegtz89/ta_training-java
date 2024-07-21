package com.epam.training.student_josegutierrez.pages.Task_3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Base class for all page objects.
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
}