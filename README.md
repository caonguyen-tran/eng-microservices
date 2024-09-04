
# ENG-Microservices
Overview
This application is designed to enhance English language skills, focusing on vocabulary learning and practicing exercises similar to the TOEIC exam. The application offers the following features:

- Learning Vocabulary: Users can create and manage collections of vocabulary for personalized study.
- Exercise Practice (QUIZ): Users can practice exercises modeled after TOEIC exam questions, categorized by each part of the exam.
- User-Generated Content: Users can add, modify, and manage their own collections of vocabulary and questions, utilizing full CRUD functionality.
- Community Interaction: Other users can view, react to, and review these collections, providing feedback and ratings on their quality.

System Architecture
The system is built using a Microservices architecture, ensuring scalability, maintainability, and independent deployment of services. This architecture divides the application into distinct services, each responsible for a specific domain function.

Technologies Used
Spring Boot 3: The backbone of the microservices, providing a robust and modern framework for building Java applications.
Docker: Containerization of each microservice ensures consistent environments across different stages of development, testing, and production.
Jenkins: Continuous Integration and Continuous Deployment (CI/CD) pipelines are managed using Jenkins, automating the building, testing, and deployment processes.
