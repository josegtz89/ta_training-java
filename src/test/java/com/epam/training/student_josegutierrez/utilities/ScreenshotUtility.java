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

/**
 * This class provides utilities for capturing and saving screenshots during test executions.
 */
public class ScreenshotUtility {

    /**
     * Takes a screenshot of the current state of the browser being used in the test.
     * @param driver The WebDriver instance.
     * @param fileName The base name for the screenshot file, which will be appended with a timestamp.
     */
    public static void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timestamp = formatter.format(new Date());
        String targetDirectory = System.getProperty("user.dir") + "/target/screenshots";
        Path targetPath = Paths.get(targetDirectory, fileName + "_" + timestamp + ".png");
        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(screenshot.toPath(), targetPath);
            System.out.println("Screenshot saved to: " + targetPath.toString());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}