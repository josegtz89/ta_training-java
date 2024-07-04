package com.epam.training.student_josegutierrez.Task_2;

import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinHome;
import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinOptions;
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
    public void TestNewPasteDataEntry() {
        String expectedTitle = "how to gain dominance among developers";
        String expectedContent = """
            git config --global user.name "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force
            """;

        pastebinHome.open();
        pastebinHome.enterDetails(expectedContent, expectedTitle);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-name")));
        WebElement contentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-text")));

        assertEquals(expectedTitle, titleField.getAttribute("value"), "Title field does not contain the expected text.");
        assertEquals(expectedContent, contentField.getAttribute("value"), "Content field does not contain the expected text.");
    }

    @Test
    public void TestNewPasteSubmit() {
        String expectedTitle = "how to gain dominance among developers";
        String expectedContent = """
            git config --global user.name "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force
            """;
        String expectedSyntax = "Bash";

        pastebinHome.open();
        pastebinHome.enterDetails(expectedContent, expectedTitle);
        pastebinOptions.setSyntaxHighlightingBash();
        pastebinOptions.setExpiration10Minutes();
        pastebinOptions.submitPaste();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pasteTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        WebElement pasteContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".source .bash")));
        WebElement syntaxLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='/archive/bash']")));

        assertEquals(expectedTitle, pasteTitle.getText(), "Paste Title does not match expected after submit.");
        assertTrue(pasteContent.getText().contains(expectedContent), "Paste Content does not match expected after submission.");
        assertTrue(syntaxLabel.getText().contains(expectedSyntax), "Syntax highlighting for Bash is not set correctly.");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
