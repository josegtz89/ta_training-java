package com.epam.training.student_josegutierrez.Task_1;

import com.epam.training.student_josegutierrez.pageobjects.Task_1.PasteBinHome;
import com.epam.training.student_josegutierrez.pageobjects.Task_1.PasteBinOptions;
import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class NewPasteTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private PasteBinHome pastebinhome;
    private PasteBinOptions pastebinoptions;

    @BeforeEach
    public void setUp() {
        driver = DriverSetup.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        pastebinhome = new PasteBinHome(driver);
        pastebinoptions = new PasteBinOptions(driver);
    }

    @Test
    public void testCreateNewPaste() {
        pastebinhome.open();
        pastebinhome.enterDetails("Hello from WebDriver", "helloweb");
        pastebinoptions.setExpiration10Minutes();

        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[@class='select2-selection__rendered' and contains(text(),'10 Minutes')]")));
        assertEquals("10 Minutes", selectedOption.getText(), "Expiration not set to 10 Minutes");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
