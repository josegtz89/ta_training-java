package com.epam.training.student_josegutierrez.locators;

public interface PasteBinLocators {
    String PASTE_AREA = "postform-text";
    String PASTE_NAME = "postform-name";
    String EXPIRATION_DROPDOWN = "//label[text()='Paste Expiration:']/following-sibling::div//span[@class='select2-selection select2-selection--single']";
    String EXPIRATION_OPTION_10_MIN = "//li[contains(text(),'10 Minutes')]";
}
