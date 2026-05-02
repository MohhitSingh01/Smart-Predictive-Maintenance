package com.example.maintenance.controller;

import com.example.maintenance.dto.JetEngineRequest;
import com.example.maintenance.dto.PredictionResponse;
import com.example.maintenance.service.MlClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PredictionController {

    // === Thresholds (keep in sync with model evaluation) ===
    private static final double ALERT_THRESHOLD = 0.20;
    private static final double MEDIUM_THRESHOLD = 0.20;
    private static final double HIGH_THRESHOLD = 0.60;


    private final MlClientService mlClientService;

    public PredictionController(MlClientService mlClientService) {
        this.mlClientService = mlClientService;
    }

    @PostMapping("/predict")
    public ResponseEntity<PredictionResponse> predict(@RequestBody JetEngineRequest request) {

        // Call ML service (Python) via MlClientService
        PredictionResponse responseFromMl = mlClientService.getPrediction(request);

        // Base model probability from FastAPI
        double pModel = responseFromMl.getProbability(); // 0.0–1.0

        double cycles = request.getCyclesSinceMaintenance();
        double vibration = request.getVibrationLevel();
        int previousFailures = request.getPreviousFailures();

        // ====== 1) Adjust probability with domain knowledge ======
        double p = pModel;

        // Previous failures should strongly increase risk
        if (previousFailures >= 5) {
            p = Math.max(p, 0.70);    // very bad history → at least 70% risk
        } else if (previousFailures >= 1) {
            p = Math.max(p, 0.40);    // any prior failure → at least 40% risk
        }

        // Many cycles since maintenance → add some risk
        if (cycles >= 300) {
            p = Math.min(1.0, p + 0.10);
        }
        if (cycles >= 500) {
            p = Math.min(1.0, p + 0.15);
        }

        // High vibration → strong signal of imminent failure
        if (vibration >= 0.6) {
            p = Math.min(1.0, p + 0.20);
        }

        // ====== 2) Apply thresholds on adjusted probability ======

        // Thresholds tuned from C-MAPSS evaluation
        final double ALERT_THRESHOLD = 0.20;  // 20%
        final double MEDIUM_THRESHOLD = 0.20; // start of MEDIUM band
        final double HIGH_THRESHOLD = 0.60; // start of HIGH band

        // Risk band
        String risk;
        if (p >= HIGH_THRESHOLD) {
            risk = "HIGH";
        } else if (p >= MEDIUM_THRESHOLD) {
            risk = "MEDIUM";
        } else {
            risk = "LOW";
        }

        // Binary prediction: should we alert maintenance?
        int prediction = (p >= ALERT_THRESHOLD) ? 1 : 0;

        // ====== 3) Build final response ======
        PredictionResponse finalResponse = new PredictionResponse();
        finalResponse.setPrediction(prediction);
        finalResponse.setProbability(p);   // send adjusted probability to frontend
        finalResponse.setRiskLevel(risk);

        return ResponseEntity.ok(finalResponse);
    }
}
