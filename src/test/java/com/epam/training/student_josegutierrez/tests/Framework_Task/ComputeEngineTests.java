package com.epam.training.student_josegutierrez.tests.Framework_Task;

import com.epam.training.student_josegutierrez.pageobjects.Framework_Task.*;
import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;

/**
 * Test suite for verifying the Google Cloud Pricing Calculator's Compute Engine form.
 */
public class ComputeEngineTests {
    private static WebDriver driver;
    private static CloudHomePage cloudHomePage;
    private static SearchResultsPage searchResultsPage;
    private static CalculatorHomePage calculatorHomePage;
    private static ComputeEngineForm computeEngineForm;
    private static EstimateSummaryPage estimateSummaryPage;

    /**
     * Initializes WebDriver and page objects before all tests.
     */
    @Before
    public void setUpClass() {
        driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        cloudHomePage = new CloudHomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        calculatorHomePage = new CalculatorHomePage(driver);
        computeEngineForm = new ComputeEngineForm(driver);
        estimateSummaryPage = new EstimateSummaryPage(driver);
        cloudHomePage.open();
    }

    /**
     * Test to verify that all components of the Compute Engine form are correctly entered/selected and validated in the summary.
     */
    @Test
    public void testComputeEngineEstimateCreation() throws InterruptedException {
        // Navigate to Google Cloud homepage and perform a search
        CloudHomePage.searchFor("Google Cloud Platform Pricing Calculator");

        // Select the calculator from the search results
        searchResultsPage.goToPricingCalculator();

        // Proceed with adding an estimate and selecting compute engine
        calculatorHomePage.addToEstimate();
        calculatorHomePage.selectComputeEngine();

        // Number of Instances selection
        String expectedInstances = "4";
        computeEngineForm.setNumberOfInstances(expectedInstances);

        // Operating System selection
        String expectedOS = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
        computeEngineForm.clickElement(computeEngineForm.operatingSystemDropdown, "Operating System Dropdown");
        WebElement osOption = driver.findElement(By.cssSelector("li[data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']"));
        computeEngineForm.clickElement(osOption, "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License) Option");

        // Machine Family selection
        computeEngineForm.clickElement(computeEngineForm.machineFamilyDropdown, "Machine Family Dropdown");
        WebElement familyOption = driver.findElement(By.cssSelector("li[data-value='general-purpose']"));
        computeEngineForm.clickElement(familyOption, "General Purpose Option");

        // Series selection
        computeEngineForm.clickElement(computeEngineForm.seriesDropdown, "Series Dropdown");
        WebElement seriesOption = driver.findElement(By.cssSelector("li[data-value='n1']"));
        computeEngineForm.clickElement(seriesOption, "N1 Series Option");

        // Machine Type selection
        String expectedMachineType = "n1-standard-8";
        computeEngineForm.clickElement(computeEngineForm.machineTypeDropdown, "Machine Type Dropdown");
        WebElement machineTypeOption = driver.findElement(By.cssSelector("li[data-value='n1-standard-8']"));
        computeEngineForm.clickElement(machineTypeOption, "n1-standard-8 Machine Type Option");

        // Add GPUs toggle
        computeEngineForm.toggleAddGpus();

        // GPU Model selection
        String expectedGpuModel = "NVIDIA V100";
        computeEngineForm.clickElement(computeEngineForm.gpuModelDropdown, "GPU Model Dropdown");
        WebElement gpuModelOption = driver.findElement(By.cssSelector("li[data-value='nvidia-tesla-v100']"));
        computeEngineForm.clickElement(gpuModelOption, "NVIDIA Tesla V100 GPU Model Option");

        // Number of GPUs selection
        String expectedGpuCount = "1";
        computeEngineForm.clickElement(computeEngineForm.numberOfGpusDropdown, "Number of GPUs Dropdown");
        WebElement numberOfGpusOption = driver.findElement(By.cssSelector("li[data-value='1']"));
        computeEngineForm.clickElement(numberOfGpusOption, "1 GPU Option");

        // Local SSD selection
        String expectedLocalSSD = "2x375 GB";
        computeEngineForm.clickElement(computeEngineForm.localSSDDropdown, "Local SSD Dropdown");
        WebElement localSSDOption = driver.findElement(By.xpath("//li[contains(@class, 'MCs1Pd') and .//span[contains(text(), '2x375 GB')]]"));
        computeEngineForm.clickElement(localSSDOption, "2x375 GB Local SSD Option");

        // Region selection
        String expectedRegion = "Netherlands (europe-west4)";
        computeEngineForm.clickElement(computeEngineForm.regionDropdown, "Region Dropdown");
        WebElement regionOption = driver.findElement(By.cssSelector("li[data-value='europe-west4']"));
        computeEngineForm.clickElement(regionOption, "Netherlands (europe-west4) Region Option");

        // Discount selection
        String expectedDiscount = "1 year";
        WebElement oneYearDiscountOption = driver.findElement(By.cssSelector("label[for='1-year']"));
        computeEngineForm.clickElement(oneYearDiscountOption, "1 Year Discount Option");

        // Static wait to ensure the page has loaded
        Thread.sleep(2000);

        // Share button click
        computeEngineForm.clickShareButton();

        // Open Estimate Summary page
        computeEngineForm.openEstimateSummary();

        // Check and switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        // Assertions
        Assert.assertTrue("Cost Estimate Summary is not visible", estimateSummaryPage.isCostEstimateSummaryVisible());
        Assert.assertEquals("Instance count mismatch", expectedInstances, estimateSummaryPage.getNumberOfInstances());
        Assert.assertEquals("Operating system mismatch", expectedOS, estimateSummaryPage.getOperatingSystem());
        Assert.assertTrue("Machine type mismatch", estimateSummaryPage.getMachineType().contains(expectedMachineType));
        Assert.assertTrue("GPU should be enabled", estimateSummaryPage.isAddGpusEnabled());
        Assert.assertEquals("GPU model mismatch", expectedGpuModel, estimateSummaryPage.getGpuModel());
        Assert.assertEquals("GPU count mismatch", expectedGpuCount, estimateSummaryPage.getNumberOfGpus());
        Assert.assertEquals("Local SSD mismatch", expectedLocalSSD, estimateSummaryPage.getLocalSSD());
        Assert.assertEquals("Region mismatch", expectedRegion, estimateSummaryPage.getRegion());
        Assert.assertEquals("Discount mismatch", expectedDiscount, estimateSummaryPage.getCommittedUseDiscount());
    }

    /**
     * Cleans up the WebDriver instance after all tests are completed.
     */
    @After
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}