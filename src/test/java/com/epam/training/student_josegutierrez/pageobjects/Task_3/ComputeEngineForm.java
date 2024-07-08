package com.epam.training.student_josegutierrez.pageobjects.Task_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object for interacting with the Compute Engine form on the Google Cloud Pricing Calculator.
 */
public class ComputeEngineForm extends BasePage {

    @FindBy(xpath = "//input[@id='c19']")
    private WebElement numberOfInstancesInput;

    @FindBy(css = "div[data-path='instance'] .VfPpkd-t08AT-Bz112c")
    private WebElement operatingSystemDropdown;

    @FindBy(css = "li[data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']")
    private WebElement operatingSystemOption;

    @FindBy(xpath = "//*[@id='regular']")
    private WebElement provisioningModel;

    @FindBy(css = "div[data-path='machine_family'] .VfPpkd-t08AT-Bz112c")
    private WebElement machineFamilyDropdown;

    @FindBy(css = "li[data-value='general-purpose']")
    private WebElement machineFamilyOption;

    @FindBy(css = "div[data-path='series'] .VfPpkd-t08AT-Bz112c")
    private WebElement seriesDropdown;

    @FindBy(css = "li[data-value='n1']")
    private WebElement seriesOption;

    @FindBy(css = "div[data-path='machine_type'] .VfPpkd-t08AT-Bz112c")
    private WebElement machineTypeDropdown;

    @FindBy(css = "li[data-value='n1-standard-8']")
    private WebElement machineTypeOption;

    @FindBy(css = "button[role='switch'][aria-label='Add GPUs']")
    private WebElement addGpuButton;

    @FindBy(css = ".VfPpkd-aPP78e[aria-label='GPU type']")
    private WebElement gpuTypeDropdown;

    @FindBy(css = "li[data-value='nvidia-tesla-v100']")
    private WebElement gpuTypeOption;

    @FindBy(css = "div[data-path='number_of_gpus'] .VfPpkd-t08AT-Bz112c")
    private WebElement numberOfGpusDropdown;

    @FindBy(css = "li[data-value='1']")
    private WebElement oneGpuOption;

    @FindBy(css = ".VfPpkd-aPP78e[aria-label='Local SSD']")
    private WebElement localSSDDropdown;

    @FindBy(css = "li[data-value='2']")
    private WebElement localSSDOption;

    @FindBy(css = "span[data-icon='arrow_drop_down'][aria-label='Region']")
    private WebElement regionDropdown;

    @FindBy(css = "li[data-value='europe-west4']")
    private WebElement regionNetherlandsOption;

    @FindBy(xpath = "//input[@id='1-year']")
    private WebElement oneYearDiscountOption;

    @FindBy(css = "span[jsname='V67aGc']")
    private WebElement shareButton;

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
            System.out.println("Number of instances set successfully.");
        } catch (Exception e) {
            System.out.println("Error setting number of instances: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the operating system dropdown and selects the desired option.
     */
    public void openOperatingSystemDropdown() {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(operatingSystemDropdown));
            dropdown.click();
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(operatingSystemOption));
            option.click();
            System.out.println("Operating system selected successfully.");
        } catch (Exception e) {
            System.out.println("Error interacting with operating system dropdown: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects the 'Regular' provisioning model.
     */
    public void selectProvisioningModel() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(provisioningModel)).click();
            System.out.println("Provisioning model 'Regular' selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting provisioning model: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the machine family dropdown and selects the specified option.
     */
    public void openMachineFamilyDropdown() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(machineFamilyDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(machineFamilyOption)).click();
            System.out.println("Machine family selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting machine family: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the series dropdown and selects the specified option.
     */
    public void selectSeries() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(seriesDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(seriesOption)).click();
            System.out.println("Series selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting series: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the machine type dropdown and selects the specified option.
     */
    public void openMachineTypeDropdown() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(machineTypeDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(machineTypeOption)).click();
            System.out.println("Machine type selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting machine type: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enables the Add GPUs option in the form.
     */
    public void toggleAddGpus() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addGpuButton)).click();
            System.out.println("Add GPUs toggled successfully.");
        } catch (Exception e) {
            System.out.println("Failed to toggle the Add GPUs button: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects the desired GPU type from the dropdown.
     */
    public void selectGpuType() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(gpuTypeDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(gpuTypeOption)).click();
            System.out.println("GPU type selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting GPU type: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Selects the number of GPUs from the dropdown.
     */
    public void selectNumberOfGpus()  {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(numberOfGpusDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(oneGpuOption)).click();
            System.out.println("Number of GPUs selected successfully.");
        } catch (Exception e) {
            System.out.println("Error selecting the number of GPUs: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Opens the 'Local SSD' dropdown and selects '2x375 GB'.
     */
    public void selectLocalSsd() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(localSSDDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(localSSDOption)).click();
            System.out.println("Local SSD selected successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to select local SSD option", e);
        }
    }

    /**
     * Selects the region by opening the dropdown and clicking the specified option.
     */
    public void selectRegion() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(regionDropdown)).click();
            wait.until(ExpectedConditions.elementToBeClickable(regionNetherlandsOption)).click();
            System.out.println("Region selected successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to select region option", e);
        }
    }

    /**
     * Selects the "1 year" discount option.
     */
    public void selectOneYearDiscount() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(oneYearDiscountOption)).click();
            System.out.println("Discount selected successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to select discount option", e);
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
     * Helper method to select an option from a dropdown.
     * @param dropdown The dropdown WebElement.
     * @param optionText The text of the option to select.
     */
    private void selectOption(WebElement dropdown, String optionText) {
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), '" + optionText + "')]")));
        option.click();
    }
}