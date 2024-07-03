package com.epam.training.student_josegutierrez.pageobjects.Task_1;

import com.epam.training.student_josegutierrez.locators.PasteBinLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasteBinHome implements PasteBinLocators {
    private WebDriver driver;

    @FindBy(id = PASTE_AREA)
    private WebElement pasteArea;

    @FindBy(id = PASTE_NAME)
    private WebElement pasteName;

    public PasteBinHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://pastebin.com");
    }

    public void enterDetails(String code, String title) {
        pasteArea.sendKeys(code);
        pasteName.sendKeys(title);
    }
}
