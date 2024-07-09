package com.epam.training.student_josegutierrez.pageobjects.Task_3;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page object for interacting with the Compute Engine form on the Google Cloud Pricing Calculator.
 */
public class ComputeEngineForm extends BasePage {

    @FindBy(css = "input[jsname='YPqjbf'][type='number']")
    public WebElement numberOfInstancesInput;

    @FindBy(xpath = "//span[contains(text(), 'Operating System / Software')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement operatingSystemDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Machine Family')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement machineFamilyDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Series')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement seriesDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Machine type')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement machineTypeDropdown;

    @FindBy(xpath = "//button[@role='switch' and @jsname='DMn7nd' and @aria-label='Add GPUs']")
    private WebElement addGpusSwitch;

    @FindBy(xpath = "//span[contains(text(), 'GPU Model')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement gpuModelDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Number of GPUs')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement numberOfGpusDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Local SSD')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement localSSDDropdown;

    @FindBy(xpath = "//span[contains(text(), 'Region')]/ancestor::div[contains(@role, 'combobox')]")
    public WebElement regionDropdown;

    @FindBy(css = "button[aria-label='Open Share Estimate dialog']")
    private WebElement shareButton;

    @FindBy(css = "a[track-name='open estimate summary']")
    private WebElement openEstimateSummaryButton;




    /**
     * Constructor to initialize the ComputeEngineForm within the driver context.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public ComputeEngineForm(WebDriver driver) {
        super(driver);
    }

    /**
     * Sets the number of instances in the Compute Engine form.
     * @param numberOfInstances The number of instances to set.
     */
    public void setNumberOfInstances(String numberOfInstances) {
        try {
            wait.until(ExpectedConditions.visibilityOf(numberOfInstancesInput));
            numberOfInstancesInput.clear();
            numberOfInstancesInput.sendKeys(numberOfInstances);
            System.out.println("Number of instances set to: " + numberOfInstances);
        } catch (Exception e) {
            System.out.println("Error setting number of instances: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enables or disables the Add GPUs option in the form.
     */
    public void toggleAddGpus() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addGpusSwitch)).click();
            System.out.println("Add GPUs toggled successfully.");
        } catch (Exception e) {
            System.out.println("Failed to toggle the Add GPUs button: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks on the 'Share' button to view the total estimate cost.
     */
    public void clickShareButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(shareButton)).click();
            System.out.println("Share button clicked successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on the Share button", e);
        }
    }

    /**
     * Clicks on the 'Open Estimate Summary' button to navigate to the estimate summary page.
     */
    public void openEstimateSummary() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(openEstimateSummaryButton)).click();
            System.out.println("Estimate summary page opened successfully.");
        } catch (Exception e) {
            System.out.println("Failed to open Estimate Summary: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks on a specified web element and logs the action.
     *
     * @param element     the web element to be clicked.
     * @param elementName the name of the element for logging purposes.
     */
    public void clickElement(WebElement element, String elementName) {
        try {
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            clickableElement.click();
            System.out.println(elementName + " selected successfully.");
        } catch (Exception e) {
            System.out.println("Error clicking " + elementName + ": " + e.getMessage());
            throw e;
        }
    }
}