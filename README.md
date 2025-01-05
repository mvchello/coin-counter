## Overview
This project implements a **Coin Counter Application** that calculates the minimum number of coins required to make up a target amount based on user-provided denominations. It was developed as part of the Oracle Technical Assessment.

- **Frontend:** React
- **Backend:** Spring Boot
- **Deployment:** Dockerized application

The project includes two challenges:
1. Implementing the coin counter logic.
2. Deploying the application in a Dockerized environment.

---

## Features
- Calculates the minimum number of coins to match the target amount.
- Validates user inputs for correctness (e.g., valid denominations and target amounts).
- Displays error messages for invalid inputs.
- Responsive UI.

---

## Prerequisites
Before running the application, ensure the following are installed on your system:
- **Docker** and **Docker Compose**
- **Java 17**
- **Node.js (v18)**

---

## Setup and Running the Application

### Step 1: Clone the Repository
```bash
git clone https://github.com/mvchello/coin-counter.git
cd coin-counter
```

### Step 2: Build and Run the Containers
```bash
docker-compose up --build
```

- **Frontend:** Accessible at `http://localhost:3000`
- **Backend:** Accessible at `http://localhost:8080/api/coins/minimum`

### Step 3: Test the Application
- Navigate to `http://localhost:3000` in your browser.
- Input a target amount and coin denominations, then click **Calculate**.

---

## Project Structure
```
.
├── backend
│   ├── src/main/java/com/example/demo
│   │   ├── CoinCalculator.java
│   │   ├── CoinCalculatorController.java
│   │   ├── CoinRequest.java
│   │   ├── SecurityConfig.java
│   │   ├── WebConfig.java
│   │   └── DemoApplication.java
│   ├── src/main/resources
│   │   └── application.properties
│   └── Dockerfile
├── frontend
│   ├── src
│   │   ├── CoinCalculator.js
│   │   └── CoinCalculator.css
│   ├── public
│   │   └── index.html
│   ├── Dockerfile
│   └── package.json
└── docker-compose.yml
```

---

## Assumptions
- Denominations are assumed to be positive numbers.
- Target amounts must be between 0 and 10,000.
