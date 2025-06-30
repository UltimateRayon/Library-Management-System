# 📚 Library Management System

A simple Java-based Library Management System that allows users to register, log in, view available books, check out and return books, and view account information. The system is built using Java, PostgreSQL, and follows a modular structure using DAO, service layers, and model classes. Initially console-based, with potential for future GUI enhancement.

---

## 🛠 Project Overview

- A clean, Java & PostgreSQL‑powered system for managing library lending
- Secure sign‑up and sign‑in with hashed passwords and built‑in duplicate‑ID checks
- Effortless browsing or searching of available books
- Smooth borrow/return workflow with a gentle 15‑day due‑date reminder and fine handling
- Personalized dashboard showing your profile and active fine

---

## 🧾 Features

- **Hassle‑free Registration**: prevents duplicate IDs and stores passwords securely with hashing
- **Safe Login**: checks your password hash before you enter
- **Book Discovery**: browse all titles or quickly search by name
- **Borrow & Return**: check out books, track availability, and apply a mild fine after 15 days
- **My Library Overview**: view your user details (excluding password) and active fines


---

## 🗃 Technologies Used

| Technology       | Purpose                           |
|------------------|-----------------------------------|
| Java (JDK 17+)   | Main programming language         |
| IntelliJ IDEA    | Code editor / IDE                 |
| PostgreSQL       | Database                          |
| Maven (+ JDBC)   | Dependency & project management   |
| JavaFX (GUI)     | Graphical User Interface          |
| Git + GitHub     | Version control and collaboration |

---

## 📈 Project Completion

🖥️ Console Version: **100%**  
🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩🟩

🎨 GUI (JavaFX) Version: **40%**  
🟩🟩🟩🟩🟩🟩🟩🟩⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜


---

## 🚀 How to Run This Project

Follow the steps below to run the Library Management System locally using Maven and PostgreSQL:

### 🧰 Prerequisites

- Java 17 or higher (JDK 21+ supported)
- Maven (3.8+ recommended)
- PostgreSQL installed and running
- IntelliJ IDEA or any Java IDE (recommended)

---

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
   ```

---

### 🖥️ Running Console-Based Version

In terminal (from the project root):

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.library.Main"
```

OR in IntelliJ IDEA:

- Right-click on `Main.java` → Click “Run”

💡 Tip: If Maven asks for the exec plugin, add this snippet to your `pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.mojo</groupId>
      <artifactId>exec-maven-plugin</artifactId>
      <version>3.1.0</version>
    </plugin>
  </plugins>
</build>
```

---

## 🎨 Running GUI (JavaFX) Version with Maven

1. Make sure your `pom.xml` includes the following JavaFX dependencies:

```xml
<dependencies>
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.1</version>
  </dependency>
  <dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>21.0.1</version>
  </dependency>
</dependencies>
```

2. Add the JavaFX Maven plugin to your `pom.xml`:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-maven-plugin</artifactId>
      <version>0.0.8</version>
      <configuration>
        <mainClass>com.library.ui.gui.GuiMain</mainClass>
      </configuration>
    </plugin>
  </plugins>
</build>
```

3. To run the JavaFX GUI version, use the Maven command:

```bash
mvn clean javafx:run
```

Or open the Maven Tool Window in IntelliJ:

- Go to `View → Tool Windows → Maven` (or press `Alt + 8`)
- Expand `Plugins → javafx → javafx:run` → Double-click to launch

✅ This will launch the GUI-based interface without needing manual VM options.

---

## 📄 License

This project is part of an academic submission under the supervision of Lecturer Sobhana Jahan, Department of Computer Science and Engineering, Faculty of Science and Technology, Bangladesh University of Professionals (BUP).

It is intended strictly for educational and non-commercial use.
