package com.epam.training.student_josegutierrez.pageobjects.Task_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Page object for the main Pastebin page where pastes are created.
 * This class encapsulates the functionality required to create a new paste,
 * including entering the paste details, setting syntax highlighting, setting expiration,
 * and submitting the paste.
 */
public class PasteBinHome {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     * @param driver The WebDriver instance for browser manipulation.
     */
    public PasteBinHome(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Opens the PasteBin home page.
     */
    public void open() {
        driver.get("https://pastebin.com");
    }

    /**
     * Enters the details of the new paste including the code and title.
     * @param code The code to be pasted.
     * @param title The title of the paste.
     */
    public void enterDetails(String code, String title) {
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-name")));
        WebElement codeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postform-text")));

        titleField.clear();
        titleField.sendKeys(title);
        codeField.clear();
        codeField.sendKeys(code);
    }

    /**
     * Sets the syntax highlighting for the paste to Bash.
     */
    public void setSyntaxHighlightingBash() {
        WebElement syntaxDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("postform-format")));
        syntaxDropdown.click();
        WebElement bashOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Bash']")));
        bashOption.click();
    }

    /**
     * Sets the expiration of the paste to "10 Minutes".
     */
    public void setExpiration10Minutes() {
        WebElement expirationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("postform-expiration")));
        expirationDropdown.click();
        WebElement tenMinutesOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='10 Minutes']")));
        tenMinutesOption.click();
    }

    /**
     * Submits the paste creation form.
     */
    public void submitPaste() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        submitButton.click();
    }
}