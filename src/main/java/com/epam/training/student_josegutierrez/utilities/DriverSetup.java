package com.epam.training.student_josegutierrez.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class DriverSetup {
    public static WebDriver getDriver() {
        return new ChromeDriver();
    }
}
