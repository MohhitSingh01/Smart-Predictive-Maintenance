package com.example.maintenance.service;

import com.example.maintenance.dto.JetEngineRequest;
import com.example.maintenance.dto.MlServiceResponse;
import com.example.maintenance.dto.PredictionResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MlClientService {

    private static final String PYTHON_URL = "http://localhost:8001/predict";

    public PredictionResponse getPrediction(JetEngineRequest request) {

        // Build request body
        Map<String, Object> body = new HashMap<>();
        body.put("values", new double[]{
                request.getCyclesSinceMaintenance(),
                request.getAvgTurbineTemp(),
                request.getCompressorPressureRatio(),
                request.getVibrationLevel(),
                request.getFuelFlowVariation(),
                request.getPreviousFailures()
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<MlServiceResponse> response =
                restTemplate.exchange(
                        PYTHON_URL,
                        HttpMethod.POST,
                        entity,
                        MlServiceResponse.class
                );

        MlServiceResponse ml = response.getBody();

        // Map to your API response
        PredictionResponse result = new PredictionResponse();
        result.setPrediction(ml.getPrediction());
        result.setProbability(ml.getFailureProbability());
        result.setRiskLevel(ml.getRiskLevel());

        return result;
    }
}