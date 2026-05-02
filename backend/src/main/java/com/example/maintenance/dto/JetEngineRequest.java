package com.example.maintenance.dto;

import lombok.Data;

@Data
public class JetEngineRequest {
    private double cyclesSinceMaintenance;
    private double avgTurbineTemp;
    private double compressorPressureRatio;
    private double vibrationLevel;
    private double fuelFlowVariation;
    private int previousFailures;
}
