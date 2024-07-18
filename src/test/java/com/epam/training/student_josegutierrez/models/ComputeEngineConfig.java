package com.epam.training.student_josegutierrez.models;

/**
 * Model class for storing configuration data related to a Google Cloud Compute Engine instance.
 */
public class ComputeEngineConfig {
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
     * Getter and Setters for the Compute Engine instances configured.
     */
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
}