package com.epam.training.student_josegutierrez.pageobjects.Framework_Task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object for the Google Cloud Calculator homepage.
 * This class encapsulates the functionality required to interact with the Google Cloud Pricing Calculator.
 */
public class CalculatorHomePage extends BasePage {

    @FindBy(xpath = "//span[text()='Add to estimate']")
    private WebElement addToEstimateButton;
    @FindBy(xpath = "//h2[contains(text(), 'Compute Engine')]")
    private WebElement computeEngineButton;

    /**
     * Constructor to initialize the Google Cloud Calculator HomePage within the driver context.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public CalculatorHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Triggers the process to add a new estimate.
     */
    public void addToEstimate() {
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButton)).click();
    }

    /**
     * Selects the "Compute Engine" option from the modal that appears after clicking "Add to estimate."
     */
    public void selectComputeEngine() {
        wait.until(ExpectedConditions.elementToBeClickable(computeEngineButton)).click();
    }
}