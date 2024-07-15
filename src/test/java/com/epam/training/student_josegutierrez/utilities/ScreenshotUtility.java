package com.epam.training.student_josegutierrez.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    public static void takeScreenshot(WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(new Date());
        Path targetPath = Paths.get("./screenshots/", fileName + "_" + timestamp + ".png");
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(scrFile.toPath(), targetPath);
            System.out.println("Screenshot saved to: " + targetPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}