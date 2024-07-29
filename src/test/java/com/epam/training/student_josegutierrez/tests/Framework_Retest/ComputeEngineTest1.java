package com.epam.training.student_josegutierrez.tests.Framework_Retest;


import com.epam.training.student_josegutierrez.models.ComputeEngineConfig;
import com.epam.training.student_josegutierrez.pages.Framework_Task.*;
import com.epam.training.student_josegutierrez.services.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Test suite for verifying the Google Cloud Pricing Calculator's Compute Engine form.
 * This class executes tests to ensure that all components of the Compute Engine form are correctly
 * entered and selected, and that the final cost estimate summary reflects these selections accurately.
 */
public class ComputeEngineTest1 extends BaseTest {
    private static EstimateSummaryPage estimateSummaryPage;
    private static ComputeEngineConfig config;

    @DataProvider(name = "ConfigData")
    public static Object[][] provideConfigData() {
        config = ConfigCreator.populateConfigFromProperties();
        return new Object[][] { { config } };
    }

    @Test(
            description = "Verifies all components of the Compute Engine form under normal conditions",
            dataProvider = "ConfigData"
    )
    public void testBasicComputeEngineForm(ComputeEngineConfig config) throws InterruptedException {
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
        performAssertions(config);
    }

    private void performAssertions(ComputeEngineConfig config) {
        estimateSummaryPage = new EstimateSummaryPage(driver);
        Assert.assertTrue(estimateSummaryPage.isCostEstimateSummaryVisible(), "Cost Estimate Summary is not visible");
        Assert.assertEquals(estimateSummaryPage.getNumberOfInstances(), String.valueOf(config.getExpectedInstances()), "Instance count mismatch");
        Assert.assertEquals(estimateSummaryPage.getOperatingSystem(), config.getExpectedOS(), "Operating system mismatch");
        Assert.assertTrue(estimateSummaryPage.getMachineType().contains(config.getExpectedMachineType()), "Machine type mismatch");
        Assert.assertTrue(estimateSummaryPage.isAddGpusEnabled(), "GPU should be enabled");
        Assert.assertEquals(estimateSummaryPage.getGpuModel(), config.getExpectedGpuModel(), "GPU model mismatch");
        Assert.assertEquals(estimateSummaryPage.getNumberOfGpus(), String.valueOf(config.getExpectedGpuCount()), "GPU count mismatch");
        Assert.assertEquals(estimateSummaryPage.getLocalSSD(), config.getExpectedLocalSSD(), "Local SSD mismatch");
        Assert.assertTrue(estimateSummaryPage.getRegion().contains(config.getExpectedRegion()), "Region mismatch");
        Assert.assertEquals(estimateSummaryPage.getCommittedUseDiscount(), config.getExpectedDiscount(), "Discount mismatch");
    }
}