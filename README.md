
# ENG-Microservices
🧠 English Learning Application - Microservices Architecture
This is an English learning platform built using a Microservices Architecture, designed for scalability, modularity, and ease of maintenance. The system enables users to learn vocabulary, take TOEIC-like tests, interact with user-generated content, and track their learning progress intelligently.

🧩 System Overview
The application consists of multiple microservices built using Spring Boot (Java) and Python, communicating via REST and Kafka messaging. Each service is designed to perform a single responsibility and can be deployed independently.

📦 Core Services
User Service – Handles registration, authentication, and profile management.

Vocabulary Service – Manages vocabulary learning progress and spaced repetition.

Collection Service – Manages vocabulary/question collections.

Word Service – Stores user’s vocabulary learning data.

Quiz Service – Offers TOEIC reading tests (Parts 5–7).

Security Service – Centralized authorization and authentication logic.

API Gateway – Routes and secures external requests.

🧪 New Python-Based AI Services
We’ve integrated two new Python microservices to extend the intelligence of the platform:

1. NLP Service – Vocabulary Extraction from Text
Technology: Python (spaCy, NLTK, or similar NLP libraries)

Function: Extracts and analyzes vocabulary from input paragraphs.

Usage: Helps users learn words directly from reading content or uploaded documents.

2. Image Recognition Service – Vocabulary via Object Detection
Technology: Python, YOLO (You Only Look Once), OpenCV

Model: Trained on the COCO dataset for real-time object detection.

Function: Detects objects in uploaded images and suggests corresponding English vocabulary.

Use Case: Enhances learning through visual recognition, helping users associate words with real-world images.

🛠️ Technologies Used
Java + Spring Boot for main microservices

Python for AI-powered services (NLP & Computer Vision)

Kafka for asynchronous communication and handle quickly task through multi-concurency

MongoDB, MySQL for database persistence

Docker + Docker Compose for deployment

React Native for mobile frontend (in progress) / ReacctJS for Admin site

🚀 CI/CD pipeline for automated deployment
All services are containerized and deployed automatically by Gitlab CI
