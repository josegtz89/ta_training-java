package com.epam.training.student_josegutierrez.pageobjects.Misc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HelloWebDriver
{
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.example.com/");
        Thread.sleep(2000);
        driver.quit();
    }
}
