from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from pathlib import Path
import numpy as np
import joblib

app = FastAPI(title="Predictive Maintenance ML API")

# -------------------------------
# Load model + features
# -------------------------------
BASE_DIR = Path(__file__).resolve().parent.parent

MODEL_PATH = BASE_DIR / "engine_model.pkl"
FEATURE_COLS_PATH = BASE_DIR / "feature_cols.pkl"

model = joblib.load(MODEL_PATH)
feature_cols = joblib.load(FEATURE_COLS_PATH)

# -------------------------------
# Request Schema
# -------------------------------
class PredictRequest(BaseModel):
    values: list[float]


# -------------------------------
# Response Schema
# -------------------------------
class PredictResponse(BaseModel):
    prediction: int
    failureProbability: float
    riskLevel: str


# -------------------------------
# Health Check (optional but useful)
# -------------------------------
@app.get("/")
def health():
    return {"status": "ML service is running"}


# -------------------------------
# Prediction Endpoint
# -------------------------------
@app.post("/predict", response_model=PredictResponse)
def predict(req: PredictRequest):
    values = req.values

    # Validate input length
    if len(values) != 6:
        raise HTTPException(
            status_code=400,
            detail="Expected 6 values in order: "
                   "[cycles_since_maintenance, avg_turbine_temp, "
                   "compressor_pressure_ratio, vibration_level, "
                   "fuel_flow_variation, previous_failures]",
        )

    try:
        # Convert input to numpy array
        X = np.array([values])

        # Predict probability
        prob_failure = float(model.predict_proba(X)[0][1])

        # Binary prediction
        prediction = int(prob_failure > 0.5)

        # Risk classification
        if prob_failure < 0.3:
            risk = "LOW"
        elif prob_failure < 0.7:
            risk = "MEDIUM"
        else:
            risk = "HIGH"

        return PredictResponse(
            prediction=prediction,
            failureProbability=prob_failure,
            riskLevel=risk
        )

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))