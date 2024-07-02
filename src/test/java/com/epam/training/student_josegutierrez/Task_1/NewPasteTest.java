package com.epam.training.student_josegutierrez.Task_1;

import com.epam.training.student_josegutierrez.fundamental.Task_1.PasteBinHome;
import com.epam.training.student_josegutierrez.fundamental.Task_1.PasteBinOptions;
import com.epam.training.student_josegutierrez.utilities.DriverSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class NewPasteTest {
    private WebDriver driver;
    private PasteBinHome pastebinhome;
    private PasteBinOptions pastebinoptions;

    @BeforeEach
    public void setUp() {
        driver = DriverSetup.getDriver();
        pastebinhome = new PasteBinHome(driver);
        pastebinoptions = new PasteBinOptions(driver);
    }

    @Test
    public void testCreateNewPaste() {
        pastebinhome.open();
        pastebinhome.enterDetails("Hello from WebDriver", "helloweb");
        pastebinoptions.setExpiration10Minutes();
        // Add assertions here to verify the paste was created successfully
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
