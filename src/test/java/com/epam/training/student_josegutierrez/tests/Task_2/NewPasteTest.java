package com.epam.training.student_josegutierrez.tests.Task_2;

import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinHome;
import com.epam.training.student_josegutierrez.pageobjects.Task_2.PasteBinResults;
import com.epam.training.student_josegutierrez.common.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the functionality of creating a new paste on Pastebin and verifying the results.
 * This class extends BaseTest to utilize shared WebDriver setup and teardown.
 */
public class NewPasteTest extends BaseTest {

    @Test
    public void testNewPasteCreationAndValidation() {
        PasteBinHome pasteBinHome = new PasteBinHome(driver);
        PasteBinResults pasteBinResults = new PasteBinResults(driver);

        String expectedTitle = "how to gain dominance among developers";
        String expectedContent = """
            git config --global user.name "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force
            """;
        String expectedSyntax = "Bash";

        // Open Pastebin and enter details
        pasteBinHome.open();
        pasteBinHome.enterDetails(expectedContent, expectedTitle);
        pasteBinHome.setSyntaxHighlightingBash();
        pasteBinHome.setExpiration10Minutes();
        pasteBinHome.submitPaste();

        // Assertions on the results page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String actualTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1"))).getText();
        String actualContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".source .bash"))).getText();
        String actualSyntax = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='/archive/bash']"))).getText();

        assertEquals(expectedTitle, actualTitle, "Paste title does not match expected after submit.");
        assertTrue(actualContent.contains(expectedContent), "Paste content does not match expected after submission.");
        assertTrue(actualSyntax.contains(expectedSyntax), "Syntax highlighting for Bash is not set correctly.");
    }
}