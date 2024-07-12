package com.epam.training.student_josegutierrez.tests.Framework_Task;

import com.epam.training.student_josegutierrez.pageobjects.Framework_Task.*;
import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.junit.*;
import org.openqa.selenium.WebDriver;
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
    @BeforeClass
    public static void setUpClass() {
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
        int expectedInstances = 4;
        computeEngineForm.setNumberOfInstances(expectedInstances);

        // Operating System selection
        String expectedOS = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
        computeEngineForm.selectDropdownOption(computeEngineForm.operatingSystemDropdown, "free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license", "Operating System");

        // Machine Family selection
        computeEngineForm.selectDropdownOption(computeEngineForm.machineFamilyDropdown, "general-purpose", "Machine Family");

        // Series selection
        computeEngineForm.selectDropdownOption(computeEngineForm.seriesDropdown, "n1", "Series");

        // Machine Type selection
        String expectedMachineType = "n1-standard-8";
        computeEngineForm.selectDropdownOption(computeEngineForm.machineTypeDropdown, "n1-standard-8", "Machine Type");

        // Add GPUs toggle
        computeEngineForm.toggleAddGpus();

        // GPU Model selection
        String expectedGpuModel = "NVIDIA V100";
        computeEngineForm.selectDropdownOption(computeEngineForm.gpuModelDropdown, "nvidia-tesla-v100", "GPU Model");

        // Number of GPUs selection
        String expectedGpuCount = "1";
        computeEngineForm.selectDropdownOption(computeEngineForm.numberOfGpusDropdown, "1", "Number of GPUs");

        // Local SSD selection
        String expectedLocalSSD = "2x375 GB";
        computeEngineForm.selectDropdownOption(computeEngineForm.localSSDDropdown, "2x375 GB", "Local SSD");

        // Region selection
        String expectedRegion = "Netherlands (europe-west4)";
        computeEngineForm.selectDropdownOption(computeEngineForm.regionDropdown, "europe-west4", "Region");

        // Discount selection
        String expectedDiscount = "1 year";
        computeEngineForm.selectOneYearDiscount();

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
        Assert.assertEquals("Instance count mismatch", String.valueOf(expectedInstances), estimateSummaryPage.getNumberOfInstances());
        Assert.assertEquals("Operating system mismatch", expectedOS, estimateSummaryPage.getOperatingSystem());
        Assert.assertTrue("Machine type mismatch", estimateSummaryPage.getMachineType().contains(expectedMachineType));
        Assert.assertTrue("GPU should be enabled", estimateSummaryPage.isAddGpusEnabled());
        Assert.assertEquals("GPU model mismatch", expectedGpuModel, estimateSummaryPage.getGpuModel());
        Assert.assertEquals("GPU count mismatch", expectedGpuCount, estimateSummaryPage.getNumberOfGpus());
        Assert.assertEquals("Local SSD mismatch", expectedLocalSSD, estimateSummaryPage.getLocalSSD());
        Assert.assertTrue("Region mismatch", estimateSummaryPage.getRegion().contains(expectedRegion));
        Assert.assertEquals("Discount mismatch", expectedDiscount, estimateSummaryPage.getCommittedUseDiscount());
    }

    /**
     * Cleans up the WebDriver instance after all tests are completed.
     */
    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}