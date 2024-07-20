package com.epam.training.student_josegutierrez.pageobjects.Framework_Task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object for interacting with the Estimate summary page of the Google Cloud Pricing Calculator after an estimate has been created.
 * It provides methods to retrieve information about the configuration specified in the Compute Engine form.
 */
public class EstimateSummaryPage extends BasePage {

    @FindBy(xpath = "//h4[contains(@class, 'QvLFl') and contains(text(), 'Cost Estimate Summary')]")
    private WebElement costEstimateSummaryTitle;
    @FindBy(xpath = "//span[text()='Number of Instances']/following::span[@class='Kfvdz'][1]")
    private WebElement numberOfInstances;
    @FindBy(xpath = "//span[text()='Operating System / Software']/following-sibling::span[@class='Kfvdz']")
    private WebElement operatingSystem;
    @FindBy(xpath = "//span[text()='Provisioning Model']/following-sibling::span[@class='Kfvdz']")
    private WebElement provisioningModel;
    @FindBy(xpath = "//span[text()='Machine type']/following-sibling::span[@class='Kfvdz']")
    private WebElement machineType;
    @FindBy(xpath = "//span[text()='Add GPUs']/following-sibling::span[@class='Kfvdz']")
    private WebElement addGpus;
    @FindBy(xpath = "//span[text()='GPU Model']/following-sibling::span[@class='Kfvdz']")
    private WebElement gpuModel;
    @FindBy(xpath = "//span[text()='Number of GPUs']/following-sibling::span[@class='Kfvdz']")
    private WebElement numberOfGpus;
    @FindBy(xpath = "//span[text()='Local SSD']/following-sibling::span[@class='Kfvdz']")
    private WebElement localSSD;
    @FindBy(xpath = "//span[text()='Region']/following-sibling::span[@class='Kfvdz']")
    private WebElement region;
    @FindBy(xpath = "//span[text()='Committed use discount options']/following-sibling::span[@class='Kfvdz']")
    private WebElement committedUseDiscount;

    /**
     * Constructor to initialize the EstimateSummaryPage within the driver context.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public EstimateSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if the Cost Estimate Summary is visible.
     * @return true if page is visible, otherwise false.
     */
    public boolean isCostEstimateSummaryVisible() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(costEstimateSummaryTitle)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Cost Estimate Summary title is not visible: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the number of instances specified in the estimate.
     * @return Number of instances as a String.
     */
    public String getNumberOfInstances() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(numberOfInstances));
            return numberOfInstances.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the number of instances: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the selected operating system from the estimate.
     * @return Operating system as a String.
     */
    public String getOperatingSystem() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(operatingSystem));
            return operatingSystem.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the operating system: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the provisioning model used in the estimate.
     * @return Provisioning model as a String.
     */
    public String getProvisioningModel() {
        try {
            return provisioningModel.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the provisioning model: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the machine type specified in the estimate.
     * @return Machine type as a String.
     */
    public String getMachineType() {
        try {
            return machineType.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the machine type: " + e.getMessage());
            return null;
        }
    }

    /**
     * Checks if the GPUs were added to the estimate.
     * @return true if GPUs were added, otherwise false.
     */
    public boolean isAddGpusEnabled() {
        try {
            return addGpus.getText().equals("true");
        } catch (Exception e) {
            System.out.println("Failed to check if GPUs were added: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves the GPU model selected in the estimate.
     * @return GPU model as a String.
     */
    public String getGpuModel() {
        try {
            return gpuModel.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the GPU model: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the number of GPUs specified in the estimate.
     * @return Number of GPUs as a String.
     */
    public String getNumberOfGpus() {
        try {
            return numberOfGpus.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the number of GPUs: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the amount of local SSD storage configured in the estimate.
     * @return Local SSD storage amount as a String.
     */
    public String getLocalSSD() {
        try {
            return localSSD.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the local SSD information: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the region selected for the services in the estimate.
     * @return Region as a String.
     */
    public String getRegion() {
        try {
            return region.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the region: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves the committed use discount option selected in the estimate.
     * @return Committed use discount option as a String.
     */
    public String getCommittedUseDiscount() {
        try {
            return committedUseDiscount.getText();
        } catch (Exception e) {
            System.out.println("Failed to get the committed use discount option: " + e.getMessage());
            return null;
        }
    }
}