package com.epam.training.student_josegutierrez.pageobjects.Task_1;

import com.epam.training.student_josegutierrez.locators.PasteBinLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasteBinHome implements PasteBinLocators {
    private WebDriver driver;

    @FindBy(id = PASTE_TEXT)
    private WebElement pasteText;
    @FindBy(id = PASTE_TITLE)
    private WebElement pasteTitle;

    public PasteBinHome(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://pastebin.com");
    }

    public void enterDetails(String code, String title) {
        pasteText.sendKeys(code);
        pasteTitle.sendKeys(title);
    }
}
