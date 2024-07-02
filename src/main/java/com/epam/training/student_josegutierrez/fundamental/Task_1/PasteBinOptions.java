package com.epam.training.student_josegutierrez.fundamental.Task_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasteBinOptions {
    private WebDriver driver;

    @FindBy(xpath = "//select[@id='postform-expiration']/option[@value='10M']")
    private WebElement expiration10Minutes;

    public PasteBinOptions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setExpiration10Minutes() {
        expiration10Minutes.click();
    }
}
