package com.epam.training.student_josegutierrez.pageobjects.Framework_Task;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object for the Google Cloud homepage.
 * This class encapsulates the functionality required to navigate and search on the Google Cloud homepage.
 */
public class CloudHomePage extends BasePage {

    @FindBy(css = "input.mb2a7b[name='q']")
    private static WebElement searchInput;

    /**
     * Constructor to initialize the CloudHomePage within the driver context.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public CloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the Google Cloud main homepage.
     */
    public void open() {
        driver.get("https://cloud.google.com/");
    }

    /**
     * Searches for a given text using the search box on the homepage.
     * @param searchText The text to search for.
     */
    public static void searchFor(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(searchText + Keys.ENTER);
        System.out.println("Search performed for: " + searchText);
    }
}
