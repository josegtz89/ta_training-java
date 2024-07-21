package com.epam.training.student_josegutierrez.pages.Framework_Task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object for handling search results on Google Cloud website.
 * This class encapsulates the functionality required to review and click on the Google Cloud Platform Pricing Calculator.
 */
public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, 'cloud.google.com/products/calculator') and contains(@class, 'gs-title')]")
    private WebElement pricingCalculatorLink;

    /**
     * Constructor to initialize the SearchResultsPage within the driver context.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /**
     * Navigates to the Google Cloud Pricing Calculator page from the search results.
     */
    public void goToPricingCalculator() {
        wait.until(ExpectedConditions.visibilityOf(pricingCalculatorLink));
        wait.until(ExpectedConditions.elementToBeClickable(pricingCalculatorLink)).click();
        System.out.println("Navigated to the Google Cloud Platform Pricing Calculator page.");
    }
}
