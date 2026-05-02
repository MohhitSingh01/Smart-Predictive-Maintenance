package com.example.maintenance.dto;

public class PredictionRequest {

    private double cyclesSinceMaintenance;
    private double avgTurbineTemp;
    private double compressorPressureRatio;
    private double vibrationLevel;
    private double fuelFlowVariation;
    private double previousFailures;

    public double getCyclesSinceMaintenance() {
        return cyclesSinceMaintenance;
    }

    public void setCyclesSinceMaintenance(double cyclesSinceMaintenance) {
        this.cyclesSinceMaintenance = cyclesSinceMaintenance;
    }

    public double getAvgTurbineTemp() {
        return avgTurbineTemp;
    }

    public void setAvgTurbineTemp(double avgTurbineTemp) {
        this.avgTurbineTemp = avgTurbineTemp;
    }

    public double getCompressorPressureRatio() {
        return compressorPressureRatio;
    }

    public void setCompressorPressureRatio(double compressorPressureRatio) {
        this.compressorPressureRatio = compressorPressureRatio;
    }

    public double getVibrationLevel() {
        return vibrationLevel;
    }

    public void setVibrationLevel(double vibrationLevel) {
        this.vibrationLevel = vibrationLevel;
    }

    public double getFuelFlowVariation() {
        return fuelFlowVariation;
    }

    public void setFuelFlowVariation(double fuelFlowVariation) {
        this.fuelFlowVariation = fuelFlowVariation;
    }

    public double getPreviousFailures() {
        return previousFailures;
    }

    public void setPreviousFailures(double previousFailures) {
        this.previousFailures = previousFailures;
    }
}