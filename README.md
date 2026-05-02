# 🔧 Smart Predictive Maintenance System

A full-stack machine learning system that predicts **jet engine failure risk** using real-time input parameters and provides actionable insights through an interactive dashboard.

---

## 🚀 Architecture

```
React (Frontend) → Spring Boot (Backend API) → FastAPI (ML Service) → Trained ML Model
```

---

## 🧠 Key Features

* 🔍 **Failure Prediction** using trained ML model
* 📊 **Risk Classification** (LOW / MEDIUM / HIGH)
* 📈 **Interactive Dashboard UI**
* 📉 **Feature Importance Insights**
* 📄 **Downloadable PDF Reports**
* ⚙️ **Real-time API Integration (Frontend ↔ Backend ↔ ML Service)**

---

## 🛠️ Tech Stack

| Layer      | Technology           |
| ---------- | -------------------- |
| Frontend   | React (Vite)         |
| Backend    | Spring Boot (Java)   |
| ML Service | FastAPI (Python)     |
| ML Model   | scikit-learn         |
| Data       | NASA C-MAPSS Dataset |

---

## ⚙️ How to Run Locally

### 1️⃣ Start ML Service

```bash
cd ml-service
uvicorn predict_api.main:app --reload --port 8001
```

---

### 2️⃣ Start Backend (Spring Boot)

```bash
cd backend
./mvnw spring-boot:run
```

---

### 3️⃣ Start Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## 🔌 API Endpoints

### Predict Failure

```
POST /api/predict
```

### Download Report

```
GET /report
```

---

## 📊 Input Parameters

* Cycles Since Maintenance
* Average Turbine Temperature
* Compressor Pressure Ratio
* Vibration Level
* Fuel Flow Variation
* Previous Failures

---

## 🧠 Model Details

* Algorithm: **Gradient Boosting / Random Forest (scikit-learn)**
* Output:

  * Failure Probability
  * Binary Prediction (0/1)
  * Risk Level

---

## 🔥 Highlights

* End-to-end ML system (no mock APIs)
* Real-time prediction pipeline
* Clean modular architecture
* Production-style backend integration

---

## 📌 Future Improvements

* 🔐 Authentication (JWT Login System)
* ☁️ Deployment (AWS / Render / Vercel)
* 📊 Time-series prediction (RUL model)
* 📈 Advanced analytics dashboard

