package com.epam.training.student_josegutierrez.pageobjects.Task_1;

import com.epam.training.student_josegutierrez.locators.PasteBinLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PasteBinOptions implements PasteBinLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    public PasteBinOptions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setExpiration10Minutes() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EXPIRATION_DROPDOWN)));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EXPIRATION_OPTION_10_MIN)));
        option.click();
    }
}