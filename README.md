# Student Database App (JDBC)
---

## ⚙️ Project Overview
This is a simple **console-based Java application** that allows managing student records (Add, View, Update, Delete) using **JDBC** to connect with a MySQL database.

The project demonstrates basic **CRUD operations** and serves as a mini-project for understanding how Java applications interact with relational databases.


## 📌 Setup Instructions

### 1. Create the Database 
- Run this SQL script in MySQL:
<img width="600" height="200" alt="image" src="https://github.com/user-attachments/assets/97384385-8037-4a93-bfa2-65402964cbed" />

### 2. Configure the App
- Place the MySQL Connector/J (.jar file) inside a lib folder in your project.
- This allows Java to connect to the MySQL database through JDBC.

### 3. Compile & Run
- From the terminal in your project folder:
<img width="700" height="65" alt="image" src="https://github.com/user-attachments/assets/9628e4bc-5552-46af-b03c-df7c9d25345b" />

## 🛠️ Technologies Used
- Java 
- JDBC (Java Database Connectivity)
- MySQL
  
## 🔑 Important Notes
- The *id* field is an AUTO_INCREMENT primary key.
- This means that once a record is **deleted**, its ID is **not reused.**
- This is **normal behavior** in MySQL and ensures data consistency.



---


