# Grading System in Java

A complete desktop application for academic institutions to manage **students**, **subjects**, **marks**, and **rankings** using:

- **Java 17**
- **Java Swing** (GUI)
- **MySQL Database**
- **DAO Pattern** (Clean Architecture)
- **Maven Project Structure**

Designed and implemented professionally by **Mobin Yousefi** (GitHub: https://github.com/mobinyousefi-cs). This project is written cleanly, modular, and ready for production-level expansion.

---

## 📌 Features

### 👨‍🎓 Student Management
- Add new students (Name, Registration Number, Department)
- Automatically saved to MySQL database
- Displayed in an interactive JTable

### 📘 Subject Management
- Add subjects (Code, Name, Credits)
- Display all subjects in a clean, sortable table

### 📝 Marks Entry
- Select student from dropdown
- Select subject from dropdown
- Enter obtained marks (0–100)
- Automatic validation

### 🏆 Ranking System
- Computes each student’s:
  - Total marks
  - Average marks
  - Rank based on highest average
- Displays results in a ranking table

### 🧱 Architecture
- **DAO Layer** for database operations
- **Model Classes** for Student, Subject, Mark, Ranking
- **Swing UI Layer** with multiple tabs
- Data automatically reloads after every operation

---

## 📦 Project Structure

```
grading-system/
│── pom.xml
│── schema.sql
│
├── src/main/java/com/mobinyousefi/gradingsystem/
│   ├── App.java
│   ├── DatabaseConfig.java
│   │
│   ├── model/
│   │   ├── Student.java
│   │   ├── Subject.java
│   │   ├── Mark.java
│   │   └── StudentRanking.java
│   │
│   ├── dao/
│   │   ├── StudentDao.java
│   │   ├── SubjectDao.java
│   │   └── MarkDao.java
│   │
│   ├── service/
│   │   └── GradeService.java
│   │
│   └── ui/
│       └── MainFrame.java
│
└── src/test/java/... (optional for future unit tests)
```

---

## 🛠 Requirements

| Component | Version |
|----------|----------|
| Java | 17+ |
| Maven | 3.8+ |
| MySQL | 8+ |
| JDBC Driver | Included automatically |

---

## 🗄 Database Setup

1. Open MySQL Workbench or terminal
2. Run the provided `schema.sql` file

```
mysql> SOURCE schema.sql;
```

3. Update database credentials in:
```
src/main/java/com/mobinyousefi/gradingsystem/DatabaseConfig.java
```

Example:
```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## ▶️ How to Run

### **Using Maven**
```
mvn clean package
mvn exec:java -Dexec.mainClass="com.mobinyousefi.gradingsystem.App"
```

### **Using an IDE (IntelliJ / Eclipse / NetBeans)**
- Import Maven project
- Run `App.java` directly

---

## ✨ Future Improvements (Optional)
- Export report cards as PDF
- Add authentication (Admin login)
- Add student photo upload system
- Add grade calculation based on weighted credits
- REST API + Web dashboard version

---

## 📄 License
This project is licensed under the **MIT License**.

---

## 👨‍💻 Author
**Mobin Yousefi**  
Master’s Student in Computer Science  
GitHub: https://github.com/mobinyousefi-cs

---

If you want, I can also generate:
- A professional GitHub repository description
- Commit templates
- Issue templates
- GitHub Actions CI pipeline

Just tell me! 🚀

