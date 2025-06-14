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

| Technology       | Purpose                           |
|------------------|-----------------------------------|
| Java (JDK 17+)   | Main programming language         |
| IntelliJ IDEA    | Code editor / IDE                 |
| PostgreSQL       | Database                          |
| Maven(+ JDBC)    | Database connectivity             |
| Git + GitHub     | Version control and collaboration |

---
## 🚀 How to Run This Project

Follow the steps below to run the Library Management System locally using Maven and PostgreSQL:

### 🧰 Prerequisites

- Java 17 (or Java 11/8, depending on your setup)
- Maven (3.8+ recommended)
- PostgreSQL installed and running
- IntelliJ IDEA or any Java IDE (optional but recommended)

### 🗄️ Database Setup

1. Create a PostgreSQL database, e.g., named `library_db`.
2. Open the file:  
   `src/main/resources/database/schema.sql`  
   and execute its content in your PostgreSQL client or admin tool (e.g., pgAdmin).
3. Update your database credentials in:  
   `src/main/java/com/library/utils/DatabaseConnection.java`

   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/library_db";
   private static final String USER = "your_username";
   private static final String PASSWORD = "your_password";

4. In terminal (from the project root):  
   a. Compile the project:    mvn clean compile  
   b. Run the main class:     mvn exec:java -Dexec.mainClass="com.library.Main"  
 OR, Open the project in IntelliJ IDEA:  
   a. Right-click on Main.java → Click “Run”  

  💡Tip: If Maven asks for the exec plugin, add this snippet to your `pom.xml`:
    
    <build>
     <plugins>
      <plugin>
       <groupId>org.codehaus.mojo</groupId>
       <artifactId>exec-maven-plugin</artifactId>
       <version>3.1.0</version>
      </plugin>
     </plugins>
    </build>
    
