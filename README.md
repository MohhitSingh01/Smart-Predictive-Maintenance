# 🔧 Smart Predictive Maintenance System

A full-stack ML system that predicts jet engine failure risk.

---

## 🚀 Architecture

React → Spring Boot → FastAPI → ML Model

---

## 🧠 Features

* Failure prediction
* Risk classification (LOW / MEDIUM / HIGH)
* Interactive dashboard
* Feature importance insights

---

## 🛠 Tech Stack

* React (Frontend)
* Spring Boot (Backend)
* FastAPI (ML)
* scikit-learn

---

## ⚙️ Run Project

### ML Service

cd ml-service
uvicorn predict_api.main:app --reload --port 8001

### Backend

cd backend
./mvnw spring-boot:run

### Frontend

cd frontend
npm install
npm run dev

---
