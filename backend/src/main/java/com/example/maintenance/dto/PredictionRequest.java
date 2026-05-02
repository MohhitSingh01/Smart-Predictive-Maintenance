package com.example.maintenance.dto;

import java.util.List;

public class PredictionRequest {
    private List<Double> values;

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }
}
