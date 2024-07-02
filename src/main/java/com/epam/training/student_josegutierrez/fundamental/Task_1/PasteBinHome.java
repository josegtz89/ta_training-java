package com.epam.training.student_josegutierrez.fundamental.Task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasteBinHome {
    private WebDriver driver;

    @FindBy(id = "postform-text")
    private WebElement pasteArea;

    @FindBy(id = "postform-name")
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
