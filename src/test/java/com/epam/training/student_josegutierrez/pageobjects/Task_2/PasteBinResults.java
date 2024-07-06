package com.epam.training.student_josegutierrez.pageobjects.Task_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page object for the Pastebin results page.
 */
public class PasteBinResults {
    private WebDriver driver;
    private WebDriverWait wait;

    public PasteBinResults(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Retrieves the title of the paste as displayed on the results page.
     * @return The title of the paste.
     */
    public String getPasteTitle() {
        WebElement pasteTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        return pasteTitle.getText();
    }

    /**
     * Retrieves the content of the paste as displayed on the results page.
     * @return The content of the paste.
     */
    public String getPasteContent() {
        WebElement pasteContent = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".textarea")));
        return pasteContent.getText();
    }

    /**
     * Checks if the syntax highlighting is set to Bash.
     * @return true if syntax is Bash, false otherwise.
     */
    public boolean isSyntaxHighlightingBash() {
        WebElement syntaxLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href*='/archive/bash']")));
        return syntaxLabel.isDisplayed() && syntaxLabel.getText().contains("Bash");
    }
}