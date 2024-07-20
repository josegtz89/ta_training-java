package com.epam.training.student_josegutierrez.pageobjects.Framework_Task;

import com.epam.training.student_josegutierrez.models.ComputeEngineConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object for interacting with the Compute Engine form on the Google Cloud Pricing Calculator.
 * It provides methods to interact with the elements within the Compute Engine form.
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
        PageFactory.initElements(driver, this);
    }

    /**
     * Configures the Compute Engine Form using a configuration object.
     * @param config Configuration object containing all required settings.
     */
    public void configureComputeEngine(ComputeEngineConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Config must not be null");
        }

        setNumberOfInstances(config.getNumberOfInstances());
        selectDropdownOption(operatingSystemDropdown, config.getOperatingSystem(), "Operating System");
        selectDropdownOption(machineFamilyDropdown, config.getMachineFamily(), "Machine Family");
        selectDropdownOption(seriesDropdown, config.getSeries(), "Series");
        selectDropdownOption(machineTypeDropdown, config.getMachineType(), "Machine Type");
        toggleAddGpus(config.isGpuEnabled());
        if (config.isGpuEnabled()) {
            selectDropdownOption(gpuModelDropdown, config.getGpuModel(), "GPU Model");
            selectDropdownOption(numberOfGpusDropdown, String.valueOf(config.getNumberOfGpus()), "Number of GPUs");
        }
        selectDropdownOption(localSSDDropdown, config.getLocalSSD(), "Local SSD");
        selectDropdownOption(regionDropdown, config.getRegion(), "Region");
        selectDiscount(config.getCommittedUseDiscount());
    }

    /**
     * Sets the number of instances in the Compute Engine form.
     * @param numberOfInstances The number of instances to set.
     */
    public void setNumberOfInstances(int numberOfInstances) {
        fillInputField(numberOfInstancesInput, String.valueOf(numberOfInstances), "Number of Instances");
    }

    /**
     * Fills a specified input field with a given value and logs the action.
     * @param element the input field WebElement.
     * @param value the value to be set in the input field.
     * @param fieldName the name of the field for debugging purposes.
     */
    private void fillInputField(WebElement element, String value, String fieldName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);
            System.out.println(fieldName + " set to: " + value);
        } catch (Exception e) {
            System.out.println("Error setting " + fieldName + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects an option from a dropdown element using its value.
     * @param dropdown The dropdown WebElement.
     * @param optionValue The value of the option to be selected.
     * @param dropdownName The name of the dropdown for logging purposes.
     */
    public void selectDropdownOption(WebElement dropdown, String optionValue, String dropdownName) {
        try {
            clickElement(dropdown, dropdownName + " Dropdown");
            WebElement option;
            if (dropdownName.equals("Local SSD")) {
                option = driver.findElement(By.xpath("//li[contains(@class, 'MCs1Pd') and .//span[contains(text(), '" + optionValue + "')]]"));
            } else {
                option = driver.findElement(By.cssSelector("li[data-value='" + optionValue + "']"));
            }
            clickElement(option, optionValue + " Option");
        } catch (Exception e) {
            System.out.println("Error selecting option in " + dropdownName + ": " + e.getMessage());
            throw e;
        }
    }


    /**
     * Clicks on a specified web element and logs the action.
     * @param element     the web element to be clicked.
     * @param elementName the name of the element for debugging purposes.
     */
    private void clickElement(WebElement element, String elementName) {
        try {
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
            clickableElement.click();
            System.out.println(elementName + " clicked successfully.");
        } catch (Exception e) {
            System.out.println("Error clicking " + elementName + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enables or disables the Add GPUs option in the form.
     * @param isEnabled true to enable GPUs, false to disable.
     */
    public void toggleAddGpus(boolean isEnabled) {
        try {
            WebElement GpusSwitch = wait.until(ExpectedConditions.elementToBeClickable(addGpusSwitch));
            boolean currentState = GpusSwitch.getAttribute("aria-checked").equals("true");
            if (currentState != isEnabled) {
                GpusSwitch.click();
                System.out.println("GPU toggle changed to " + isEnabled);
            }
        } catch (Exception e) {
            System.out.println("Failed to toggle the Add GPUs button: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks on a discount option based on its label attribute.
     * @param discountOption The label attribute value of the discount option.
     */
    public void selectDiscount(String discountOption) {
        try {
            WebElement discountButton = driver.findElement(By.cssSelector("label[for='116" + discountOption + "']"));
            clickElement(discountButton, discountOption + " Discount Option");
        } catch (Exception e) {
            System.out.println("Failed to select the " + discountOption + " Discount Option: " + e.getMessage());
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
}