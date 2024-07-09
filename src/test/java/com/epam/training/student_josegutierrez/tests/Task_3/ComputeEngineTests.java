package com.epam.training.student_josegutierrez.tests.Task_3;

import com.epam.training.student_josegutierrez.pageobjects.Task_3.HomePage;
import com.epam.training.student_josegutierrez.pageobjects.Task_3.ComputeEngineForm;
import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class ComputeEngineTests {
    private static WebDriver driver;
    private static HomePage homePage;
    private static ComputeEngineForm computeEngineForm;

    @BeforeClass
    public static void setUpClass() {
        driver = DriverSetup.getDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        computeEngineForm = new ComputeEngineForm(driver);
        homePage.open();
    }

    @Test
    public void testComputeEngineEstimateCreation() {

        homePage.addToEstimate();
        homePage.selectComputeEngine();
        computeEngineForm.setNumberOfInstances("4");

        // Operating System selection
        computeEngineForm.clickElement(computeEngineForm.operatingSystemDropdown, "Operating System Dropdown");
        WebElement osOption = driver.findElement(By.cssSelector("li[data-value='free-debian-centos-coreos-ubuntu-or-byol-bring-your-own-license']"));
        computeEngineForm.clickElement(osOption, "Free Operating System Option");

        // Machine Family selection
        computeEngineForm.clickElement(computeEngineForm.machineFamilyDropdown, "Machine Family Dropdown");
        WebElement familyOption = driver.findElement(By.cssSelector("li[data-value='general-purpose']"));
        computeEngineForm.clickElement(familyOption, "General Purpose Option");

        // Series selection
        computeEngineForm.clickElement(computeEngineForm.seriesDropdown, "Series Dropdown");
        WebElement seriesOption = driver.findElement(By.cssSelector("li[data-value='n1']"));
        computeEngineForm.clickElement(seriesOption, "N1 Series Option");

        // Machine Type selection
        computeEngineForm.clickElement(computeEngineForm.machineTypeDropdown, "Machine Type Dropdown");
        WebElement machineTypeOption = driver.findElement(By.cssSelector("li[data-value='n1-standard-8']"));
        computeEngineForm.clickElement(machineTypeOption, "n1-standard-8 Machine Type Option");

        // Add GPUs toggle
        computeEngineForm.toggleAddGpus();

        // GPU Model selection
        computeEngineForm.clickElement(computeEngineForm.gpuModelDropdown, "GPU Model Dropdown");
        WebElement gpuModelOption = driver.findElement(By.cssSelector("li[data-value='nvidia-tesla-v100']"));
        computeEngineForm.clickElement(gpuModelOption, "NVIDIA Tesla V100 GPU Model Option");

        // Number of GPUs selection
        computeEngineForm.clickElement(computeEngineForm.numberOfGpusDropdown, "Number of GPUs Dropdown");
        WebElement numberOfGpusOption = driver.findElement(By.cssSelector("li[data-value='1']"));
        computeEngineForm.clickElement(numberOfGpusOption, "1 GPU Option");

        // Local SSD selection
        computeEngineForm.clickElement(computeEngineForm.localSSDDropdown, "Local SSD Dropdown");
        WebElement localSSDOption = driver.findElement(By.xpath("//li[contains(@class, 'MCs1Pd') and .//span[contains(text(), '2x375 GB')]]"));
        computeEngineForm.clickElement(localSSDOption, "2x375 GB Local SSD Option");

        // Region selection
        computeEngineForm.clickElement(computeEngineForm.regionDropdown, "Region Dropdown");
        WebElement regionOption = driver.findElement(By.cssSelector("li[data-value='europe-west4']"));
        computeEngineForm.clickElement(regionOption, "Europe West4 (Netherlands) Region Option");

        // Discount selection
        //WebElement oneYearDiscountOption = driver.findElement(By.xpath("//input[@id='1-year']"));
        //computeEngineForm.clickElement(oneYearDiscountOption, "1 Year Discount Option");

        // Share button click
        computeEngineForm.clickShareButton();

        // Open Estimate Summary page
        computeEngineForm.openEstimateSummary();




    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}