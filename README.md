# 🍽️ Zayka Backend - Spring Boot + MongoDB

A smart, secure, and scalable backend built for **Zayka**, a full-stack food ordering app — developed using **Spring Boot**, **MongoDB**, and **JWT Authentication**.

---

## 🚀 Tech Stack

- **Spring Boot 3.5.3**
- **Java 21**
- **MongoDB (local)**
- **JWT Authentication**
- **RESTful APIs**
- **Spring Security**
- **Maven**

---

## ✅ Features

- 🔐 User Registration & Login with JWT
- 🍕 Food Menu APIs (GET all foods)
- 🛒 Cart System (Add/Remove/View)
- 🧾 Place Orders & View Order History
- ⚙️ Fully integrated with Android frontend

---

## 📁 Folder Structure

src/
├── controller/ // API endpoints
├── model/ // MongoDB documents
├── repository/ // MongoDB repositories
├── security/ // JWT config and filters
├── service/ // Business logic
└── ZaykaBackendApplication.java


---

## 🔧 How to Run

> ⚠️ Make sure MongoDB is running locally on `localhost:27017`

### ▶️ Run with Maven


mvn spring-boot:run
🌐 API Base URL
bash
Copy
Edit
http://localhost:8080/api/
