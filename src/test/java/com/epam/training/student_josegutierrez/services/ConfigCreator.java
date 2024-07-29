package com.epam.training.student_josegutierrez.services;


import com.epam.training.student_josegutierrez.models.ComputeEngineConfig;

public class ConfigCreator {
    /**
     * Populates the ComputeEngineConfig object with values from properties files as per the environment selected.
     */
    public static ComputeEngineConfig populateConfigFromProperties() {
        ComputeEngineConfig config = new ComputeEngineConfig();
        try {
            // Setting expected data for Compute Engine Form
            config.setNumberOfInstances(Integer.parseInt(ConfigReader.getProperty("option.instances")));
            config.setOperatingSystem(ConfigReader.getProperty("option.operatingSystem"));
            config.setMachineFamily(ConfigReader.getProperty("option.machineFamily"));
            config.setSeries(ConfigReader.getProperty("option.series"));
            config.setMachineType(ConfigReader.getProperty("option.machineType"));
            config.setGpuModel(ConfigReader.getProperty("option.gpuModel"));
            config.setNumberOfGpus(Integer.parseInt(ConfigReader.getProperty("option.numberOfGpus")));
            config.setLocalSSD(ConfigReader.getProperty("option.localSSD"));
            config.setRegion(ConfigReader.getProperty("option.region"));
            config.setCommittedUseDiscount(ConfigReader.getProperty("option.discount"));
            config.toggleGpuEnabled();

            // Setting expected data for assertions
            config.setExpectedInstances(Integer.parseInt(ConfigReader.getProperty("expected.instances")));
            config.setExpectedOS(ConfigReader.getProperty("expected.operatingSystem"));
            config.setExpectedMachineType(ConfigReader.getProperty("expected.machineType"));
            config.setExpectedGpuModel(ConfigReader.getProperty("expected.gpuModel"));
            config.setExpectedGpuCount(Integer.parseInt(ConfigReader.getProperty("expected.gpuCount")));
            config.setExpectedLocalSSD(ConfigReader.getProperty("expected.localSSD"));
            config.setExpectedRegion(ConfigReader.getProperty("expected.region"));
            config.setExpectedDiscount(ConfigReader.getProperty("expected.discount"));
        } catch (NumberFormatException e) {
            System.err.println("Error parsing number from properties: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error retrieving properties: " + e.getMessage());
        }
        return config;
    }
}
