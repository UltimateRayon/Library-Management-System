# 📚 Library Management System

A simple Java-based Library Management System that allows users to register, log in, view available books, check out and return books, and view account information. The system is built using Java, PostgreSQL, and follows a modular structure using DAO, service layers, and model classes. Initially console-based, with potential for future GUI enhancement.

---

## 🛠 Project Overview

- Console-based user interface  
- PostgreSQL database integration using JDBC  
- User authentication system (register/login)  
- Book borrowing and returning system  
- User account details with current borrow status  

---

## 🧾 Features

- ✅ User registration and login (with password security)
- 📖 View list of available books
- 📥 Check out books
- 📤 Return books
- 👤 View personal account and checkout history

---

## 🗃 Technologies Used

| Technology       | Purpose                         |
|------------------|---------------------------------|
| Java (JDK 17+)   | Main programming language       |
| IntelliJ IDEA    | Code editor / IDE               |
| PostgreSQL       | Database                        |
| JDBC             | Database connectivity           |
| Git + GitHub     | Version control and collaboration |

---

## 📁 Project Structure (Simplified)

src/
├── app/
│ ├── Main.java
│ └── Menu.java
├── model/
│ ├── User.java
│ └── Book.java
├── dao/
│ ├── UserDAO.java
│ └── BookDAO.java
├── service/
│ ├── AuthService.java
│ └── LibraryService.java
├── util/
│ ├── InputHelper.java
│ └── PasswordUtil.java

