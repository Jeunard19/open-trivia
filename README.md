# Open Trivia API – Java Backend

This project is a **Spring Boot–based intermediary API** for the Open Trivia Database.  
Its purpose is to prevent users from directly accessing the correct answers by hiding the original Open Trivia API behind a custom Java backend.

---

## Features

- Spring Boot REST API
- Two endpoints:
  - `GET /questions`
  - `POST /checkanswers`
- Docker & Docker Compose support
- Dockerized test execution
- No database required
- Clean DTO-based request/response models


---

##  API Endpoints

### Get Trivia Question

**Endpoint**
```bash
GET /api/questions
```

**Response**
```json
{
  "question": "What is the capital of France?",
  "options": [
    "Paris",
    "Berlin",
    "Madrid",
    "Rome"
  ]
}
```

**Description**
- Returns a single trivia question
- The correct answer is hidden among multiple options

### Check Answer

**Endpoint**
```bash
POST /api/checkanswers
```

**Request**
```json
{
  "question": "What is the capital of France?",
  "answer": "Paris"
}
```

**Response**
```json
{
   "correct": true
}
```

## Running the Application with Docker
 **Build & Run the Application**
```bash
docker-compose up --build
```

**The API will be available at**
```bash
http://localhost:8080
```
## Running Tests with Docker
 **Tests are fully containerised.**
 ```bash
docker compose -f docker-compose.test.yml up --build
```
##  Live Deployment

The application is deployed on Google Cloud Run:

- **Frontend:** https://frontend-220740165367.europe-west1.run.app/
- **Backend:** https://open-trivia-backend-220740165367.europe-west4.run.app









