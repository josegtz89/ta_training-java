package com.epam.training.student_josegutierrez.tests.Framework_Task;


import com.epam.training.student_josegutierrez.models.ComputeEngineConfig;
import com.epam.training.student_josegutierrez.pageobjects.Framework_Task.*;
import com.epam.training.student_josegutierrez.utilities.BaseTest;
import com.epam.training.student_josegutierrez.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

/**
 * Test suite for verifying the Google Cloud Pricing Calculator's Compute Engine form.
 * This class executes tests to ensure that all components of the Compute Engine form are correctly
 * entered and selected, and that the final cost estimate summary reflects these selections accurately.
 */
public class ComputeEngineTests extends BaseTest {
    private static CloudHomePage cloudHomePage;
    private static SearchResultsPage searchResultsPage;
    private static CalculatorHomePage calculatorHomePage;
    private static ComputeEngineForm computeEngineForm;
    private static EstimateSummaryPage estimateSummaryPage;
    private static ComputeEngineConfig config;


    /**
     * Initializes WebDriver, page objects, and navigates to the Google Cloud homepage before all tests.
     */
    @BeforeClass
    public static void setUpClass() {
        cloudHomePage = new CloudHomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        calculatorHomePage = new CalculatorHomePage(driver);
        computeEngineForm = new ComputeEngineForm(driver);
        estimateSummaryPage = new EstimateSummaryPage(driver);
        cloudHomePage.open();

    }

    /**
     * Populates the ComputeEngineConfig object with values from properties files as per the environment selected.
     */
    private static void populateConfigFromProperties() {
        try {
            config.setNumberOfInstances(Integer.parseInt(ConfigReader.getProperty("option.instances")));
            config.setOperatingSystem(ConfigReader.getProperty("option.operatingSystem"));
            config.setMachineFamily(ConfigReader.getProperty("option.machineFamily"));
            config.setSeries(ConfigReader.getProperty("option.series"));
            config.setMachineType(ConfigReader.getProperty("option.machineType"));
            config.setGpuModel(ConfigReader.getProperty("option.gpuModel"));
            config.setNumberOfGpus(Integer.parseInt(ConfigReader.getProperty("option.numberOfGpus")));
            config.setLocalSSD(ConfigReader.getProperty("option.localSSD"));
            config.setRegion(ConfigReader.getProperty("option.region"));
            config.setCommittedUseDiscount(ConfigReader.getProperty("option.discount"));
            config.toggleGpuEnabled();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number from properties: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving properties: " + e.getMessage());
        }
    }

    /**
     * Test to verify that all components of the Compute Engine form are correctly entered/selected and validated in the summary.
     */
    @Test
    public void testComputeEngineEstimateCreation() throws InterruptedException {
        // Navigate to Google Cloud homepage and perform a search
        cloudHomePage.searchFor(ConfigReader.getProperty("search.query"));

        // Select the calculator from the search results
        searchResultsPage.goToPricingCalculator();

        // Proceed with adding an estimate and selecting compute engine
        calculatorHomePage.addToEstimate();
        calculatorHomePage.selectComputeEngine();

        // Configure the Compute Engine form with the settings from the config model
        config = new ComputeEngineConfig();
        populateConfigFromProperties();
        computeEngineForm.configureComputeEngine(config);

        // Static wait to ensure the page has loaded
        Thread.sleep(2000);

        // Share button click
        computeEngineForm.clickShareButton();

        // Open Estimate Summary page
        computeEngineForm.openEstimateSummary();

        // Check and switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        // Expected data
        int expectedInstances = Integer.parseInt(ConfigReader.getProperty("expected.instances"));
        String expectedOS = ConfigReader.getProperty("expected.operatingSystem");
        String expectedMachineType = ConfigReader.getProperty("expected.machineType");
        String expectedGpuModel = ConfigReader.getProperty("expected.gpuModel");
        String expectedGpuCount = ConfigReader.getProperty("expected.gpuCount");
        String expectedLocalSSD = ConfigReader.getProperty("expected.localSSD");
        String expectedRegion = ConfigReader.getProperty("expected.region");
        String expectedDiscount = ConfigReader.getProperty("expected.discount");

        // Assertions
        Assert.assertTrue(estimateSummaryPage.isCostEstimateSummaryVisible(), "Cost Estimate Summary is not visible");
        Assert.assertEquals(estimateSummaryPage.getNumberOfInstances(), String.valueOf(expectedInstances), "Instance count mismatch");
        Assert.assertEquals(estimateSummaryPage.getOperatingSystem(), expectedOS, "Operating system mismatch");
        Assert.assertTrue(estimateSummaryPage.getMachineType().contains(expectedMachineType), "Machine type mismatch");
        Assert.assertTrue(estimateSummaryPage.isAddGpusEnabled(), "GPU should be enabled");
        Assert.assertEquals(estimateSummaryPage.getGpuModel(), expectedGpuModel, "GPU model mismatch");
        Assert.assertEquals(estimateSummaryPage.getNumberOfGpus(), expectedGpuCount, "GPU count mismatch");
        Assert.assertEquals(estimateSummaryPage.getLocalSSD(), expectedLocalSSD, "Local SSD mismatch");
        Assert.assertTrue(estimateSummaryPage.getRegion().contains(expectedRegion), "Region mismatch");
        Assert.assertEquals(estimateSummaryPage.getCommittedUseDiscount(), expectedDiscount, "Discount mismatch");
    }

    /**
     * Cleans up the WebDriver instance after all tests are completed.
     */
    @AfterClass
    public static void tearDownClass() {
        tearDown();
    }
}