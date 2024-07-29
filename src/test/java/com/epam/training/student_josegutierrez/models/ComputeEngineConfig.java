package com.epam.training.student_josegutierrez.models;

/**
 * Model class for storing configuration data related to a Google Cloud Compute Engine instance.
 */
public class ComputeEngineConfig {
    // Configuration fields
    private int numberOfInstances;
    private String operatingSystem;
    private String machineFamily;
    private String series;
    private String machineType;
    private boolean gpuEnabled;
    private String gpuModel;
    private int numberOfGpus;
    private String localSSD;
    private String region;
    private String selectedDiscount;

    // Fields for expected data
    private int expectedInstances;
    private String expectedOS;
    private String expectedMachineType;
    private String expectedGpuModel;
    private int expectedGpuCount;
    private String expectedLocalSSD;
    private String expectedRegion;
    private String expectedDiscount;

    /**
     * Getter and Setters for the Compute Engine instances configured.
     */
    public int getNumberOfInstances() {
        return numberOfInstances;
    }
    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }
    public String getOperatingSystem() {
        return operatingSystem;
    }
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    public String getMachineFamily() {
        return machineFamily;
    }
    public void setMachineFamily(String machineFamily) {
        this.machineFamily = machineFamily;
    }
    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }
    public String getMachineType() {
        return machineType;
    }
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }
    public boolean isGpuEnabled() {
        return gpuEnabled;
    }
    public String getGpuModel() {
        return gpuModel;
    }
    public void setGpuModel(String gpuModel) {
        this.gpuModel = gpuModel;
    }
    public int getNumberOfGpus() {
        return numberOfGpus;
    }
    public void setNumberOfGpus(int numberOfGpus) {
        this.numberOfGpus = numberOfGpus;
    }
    public String getLocalSSD() {
        return localSSD;
    }
    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCommittedUseDiscount() {
        return selectedDiscount;
    }
    public void setCommittedUseDiscount(String committedUseDiscount) {
        this.selectedDiscount = committedUseDiscount;
    }

    /**
     * Toggles the GPU enabled state. If GPUs are disabled, also resets the GPU model and count to defaults.
     */
    public void toggleGpuEnabled() {
        this.gpuEnabled = !this.gpuEnabled;
        if (!gpuEnabled) {
            gpuModel = null;
            numberOfGpus = 0;
        }
    }

    /**
     * Getter and Setters for the Expected Estimate Summary instances configured.
     */
    public int getExpectedInstances() { return expectedInstances; }
    public void setExpectedInstances(int expectedInstances) { this.expectedInstances = expectedInstances; }
    public String getExpectedOS() { return expectedOS; }
    public void setExpectedOS(String expectedOS) { this.expectedOS = expectedOS; }
    public String getExpectedMachineType() { return expectedMachineType; }
    public void setExpectedMachineType(String expectedMachineType) { this.expectedMachineType = expectedMachineType; }
    public String getExpectedGpuModel() { return expectedGpuModel; }
    public void setExpectedGpuModel(String expectedGpuModel) { this.expectedGpuModel = expectedGpuModel; }
    public int getExpectedGpuCount() { return expectedGpuCount; }
    public void setExpectedGpuCount(int expectedGpuCount) { this.expectedGpuCount = expectedGpuCount; }
    public String getExpectedLocalSSD() { return expectedLocalSSD; }
    public void setExpectedLocalSSD(String expectedLocalSSD) { this.expectedLocalSSD = expectedLocalSSD; }
    public String getExpectedRegion() { return expectedRegion; }
    public void setExpectedRegion(String expectedRegion) { this.expectedRegion = expectedRegion; }
    public String getExpectedDiscount() { return expectedDiscount; }
    public void setExpectedDiscount(String expectedDiscount) { this.expectedDiscount = expectedDiscount; }

}