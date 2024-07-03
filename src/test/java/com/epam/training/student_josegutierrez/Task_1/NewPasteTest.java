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
import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

public class NewPasteTest {
    private WebDriver driver;
    private PasteBinHome pastebinHome;
    private PasteBinOptions pastebinOptions;

    @BeforeEach
    public void setUp() {
        driver = DriverSetup.getDriver();
        pastebinHome = new PasteBinHome(driver);
        pastebinOptions = new PasteBinOptions(driver);
    }


    @Test
    public void testFormDataEntry() {
        String expectedTitle = "helloweb";
        String expectedContent = "Hello from WebDriver";

        pastebinHome.open();
        pastebinHome.enterDetails(expectedContent, expectedTitle);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-name")));
        WebElement contentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-text")));

        assertEquals(expectedTitle, titleField.getAttribute("value"), "Title field does not contain the expected text.");
        assertEquals(expectedContent, contentField.getAttribute("value"), "Content field does not contain the expected text.");
    }

    @Test
    public void testFormSubmissionAndValidation() {
        String expectedTitle = "helloweb";
        String expectedContent = "Hello from WebDriver";

        pastebinHome.open();
        pastebinHome.enterDetails(expectedContent, expectedTitle);
        pastebinOptions.setExpiration10Minutes();
        pastebinOptions.submitPaste();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pasteTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        WebElement pasteContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".source .text")));

        assertEquals(expectedTitle, pasteTitle.getText(), "Paste title does not match expected after submit.");
        assertTrue(pasteContent.getText().contains(expectedContent), "Paste content does not match expected after submission.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
