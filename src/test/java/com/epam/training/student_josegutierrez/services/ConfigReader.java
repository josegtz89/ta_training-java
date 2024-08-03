package com.epam.training.student_josegutierrez.services;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class provides a mechanism to load and retrieve configuration properties from
 * files based on the current execution environment.
 */
public class ConfigReader {
    private static Properties properties = new Properties();

    static {
        loadProperties();
    }

    /**
     * Loads properties from files with environment-specific properties if available.
     */
    private static void loadProperties() {
        String environment = System.getProperty("environment", "dev");
        try {
            FileInputStream propFile = new FileInputStream("src/test/resources/" + environment + ".properties");
            properties.load(propFile);
            propFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties files: " + e.getMessage());
        }
    }

    /**
     * Retrieves a property value based on its key. Returns null if the key is not found.
     * @param key The key of the property to retrieve.
     * @return The value of the property or null if the property is not found.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
