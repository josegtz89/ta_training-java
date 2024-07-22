package com.epam.training.student_josegutierrez.tests.Framework_Task;


import com.epam.training.student_josegutierrez.models.ComputeEngineConfig;
import com.epam.training.student_josegutierrez.pages.Framework_Task.*;
import com.epam.training.student_josegutierrez.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.ArrayList;

/**
 * Test suite for verifying the Google Cloud Pricing Calculator's Compute Engine form.
 * This class executes tests to ensure that all components of the Compute Engine form are correctly
 * entered and selected, and that the final cost estimate summary reflects these selections accurately.
 */
public class ComputeEngineTests extends BaseTest {
    private static EstimateSummaryPage estimateSummaryPage;
    private static ComputeEngineConfig config;


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

    @DataProvider(name = "ConfigData")
    public static Object[][] provideConfigData() {
        config = new ComputeEngineConfig();
        populateConfigFromProperties();
        return new Object[][] { { config } };
    }

    @Test(
            description = "Verifies all components of the Compute Engine form under normal conditions",
            groups = {"smoke"},
            dataProvider = "ConfigData"
    )
    public void testBasicComputeEngineForm(ComputeEngineConfig config) throws InterruptedException {
        ComputeEngineEstimateCreation(config);
    }

    @Test(
            description = "Verifies all components of the Compute Engine form under normal conditions",
            groups = {"regression"},
            dataProvider = "ConfigData"
    )
    public void testComputeEngineEstimateCreation(ComputeEngineConfig config) throws InterruptedException {
        ComputeEngineEstimateCreation(config);
    }

    @Test(
            description = "Intentionally misconfigures settings to validate error handling",
            groups = {"fail"},
            dataProvider = "ConfigData"
    )
    public void testComputeEngineFailScenario(ComputeEngineConfig config) throws InterruptedException {
        config.setNumberOfInstances(config.getNumberOfInstances() + 1);
        ComputeEngineEstimateCreation(config);
    }


    public void ComputeEngineEstimateCreation(ComputeEngineConfig config) throws InterruptedException {
        // 1. Navigate to Google Cloud homepage and perform a search
        CloudHomePage cloudHomePage = new CloudHomePage(driver);
        cloudHomePage.open();
        cloudHomePage.searchFor(ConfigReader.getProperty("search.query"));

        // 2. Select the calculator from the search results
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.goToPricingCalculator();

        // 3. Proceed with adding an estimate and selecting compute engine
        CalculatorHomePage calculatorHomePage = new CalculatorHomePage(driver);
        calculatorHomePage.addToEstimate();
        calculatorHomePage.selectComputeEngine();

        // 4. Configure the Compute Engine form with the settings from the config model
        ComputeEngineFormPage computeEngineForm = new ComputeEngineFormPage(driver);
        computeEngineForm.configureComputeEngine(config);

        // 5. Static wait to ensure the page has loaded
        Thread.sleep(2000);

        // 6. Share button click
        computeEngineForm.clickShareButton();

        // 7. Open Estimate Summary page
        computeEngineForm.openEstimateSummary();

        // 8. Check and switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));

        //9. Perform Assertion of Data
        estimateSummaryPage = new EstimateSummaryPage(driver);
        config = new ComputeEngineConfig();
        performAssertions(config);
    }

    private void performAssertions(ComputeEngineConfig expectedConfig) {
        // Expected data
        int expectedInstances = Integer.parseInt(ConfigReader.getProperty("expected.instances"));
        String expectedOS = ConfigReader.getProperty("expected.operatingSystem");
        String expectedMachineType = ConfigReader.getProperty("expected.machineType");
        String expectedGpuModel = ConfigReader.getProperty("expected.gpuModel");
        String expectedGpuCount = ConfigReader.getProperty("expected.gpuCount");
        String expectedLocalSSD = ConfigReader.getProperty("expected.localSSD");
        String expectedRegion = ConfigReader.getProperty("expected.region");
        String expectedDiscount = ConfigReader.getProperty("expected.discount");
        // Assertions for Estimate Summary Page
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
}