package com.epam.training.student_josegutierrez.fundamental.Task_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PasteBinOptions {
    private WebDriver driver;
    private WebDriverWait wait;

    public PasteBinOptions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setExpiration10Minutes() {
        // Click the dropdown to open it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Paste Expiration:']/following-sibling::div//span[@class='select2-selection select2-selection--single']")));
        dropdown.click();

        // Wait for the dropdown options to be visible and click the '10 Minutes' option
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(),'10 Minutes')]")));
        option.click();
    }
}
