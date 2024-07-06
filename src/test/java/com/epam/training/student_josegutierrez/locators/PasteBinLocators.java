package com.epam.training.student_josegutierrez.locators;

public interface PasteBinLocators {
    String PASTE_TEXT = "postform-text";
    String PASTE_TITLE = "postform-name";
    String EXPIRATION_DROPDOWN = "//label[text()='Paste Expiration:']/following-sibling::div//span[@class='select2-selection select2-selection--single']";
    String EXPIRATION_OPTION_10_MIN = "//li[contains(text(),'10 Minutes')]";
    String SYNTAX_HIGHLIGHTING_DROPDOWN = "//label[contains(text(),'Syntax Highlighting:')]/following-sibling::div//span[@class='select2-selection select2-selection--single']";
    String SYNTAX_OPTION_BASH = "//li[contains(text(),'Bash')]";
    String SUBMIT_BUTTON = "button.btn.-big[type='submit']";
}
