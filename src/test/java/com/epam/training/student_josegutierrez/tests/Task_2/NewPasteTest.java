package com.epam.training.student_josegutierrez.tests.Task_2;


import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinHome;
import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinResults;
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
    private PasteBinHome pastebinHome;
    private PasteBinResults pastebinResults;

    @BeforeEach
    public void setUp() {
        driver = DriverSetup.getDriver();
        pastebinHome = new PasteBinHome(driver);
        pastebinResults = new PasteBinResults(driver);
    }

    @Test
    public void testNewPasteCreationAndValidation() {
        String expectedTitle = "how to gain dominance among developers";
        String expectedContent = """
            git config --global user.name "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force
            """;
        String expectedSyntax = "Bash";

        pastebinHome.open();
        pastebinHome.enterDetails(expectedContent, expectedTitle);
        pastebinHome.setSyntaxHighlightingBash();
        pastebinHome.setExpiration10Minutes();
        pastebinHome.submitPaste();

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
        if (driver != null) {
            driver.quit();
        }
    }
}