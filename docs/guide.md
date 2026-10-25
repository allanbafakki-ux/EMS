# Deployment & Usage Guide

## 1. Pre‑requisites
- **Java Development Kit (JDK) 21 or newer** – ensure `java` and `javac` are on your system path.
- No external libraries or build tools are required.

## 2. Project Structure
```
EmployeeRecordManagementSystem/
├─ src/                 # All source files
├─ data/                 # SQLite CSV file for persistence (generated at runtime)
├─ docs/                 # Documentation, UML, report, etc.
├─ bin/                  # Compiled .class files (generated during build)
├─ Main.java             # Application entry point
└─ README.md
```

## 3. Build & Run
1. **Compile** (from project root):
   ```bash
   mkdir bin
   javac -d bin -sourcepath src src/com/employeems/Main.java
   ```
   - The `-sourcepath` flag tells `javac` where to look for import files; all classes are compiled automatically.

2. **Run**: 
   ```bash
   java -cp bin com.employeems.Main
   ```

3. The application launches a Swing window. The default view is the *Dashboard* tab.

## 4. Using the Application
- **Add Employee**: Navigate to *Employees* → click **Add**. Fill the fields; the employee is persisted to `data/employees.csv`.
- **Delete**: Select a row and press **Delete**. The employee is pushed onto the undo stack.
- **Undo Delete**: Use **Undo Delete** to restore the most recently deleted employee.
- **Search**: A simple search by Employee ID is available from the *Employees* tab.
- **Sort**: Click **Sort** to choose a column; the table updates using bubble‑sort.
- **Requests**: Add HR or leave requests; they are stored in a FIFO queue and processed in order.

## 5. Documentation & Report
The **docs/** folder contains:
- `UML.md` – Mermaid diagram of the class hierarchy.
- `algorithms.md` – Pseudocode for search & sorting.
- `complexity-analysis.md` – Complexity tables.
- `report.md` – 5‑10 page report including abstract, design, experiments, and discussion.
- `viva.md` – Prepared viva questions and answers.

## 6. Build Automation (Optional)
You can create a simple batch file (`build.bat`) to compile and run automatically:
```bat
@echo off
mkdir bin
javac -d bin -sourcepath src src/com/employeems/Main.java
java -cp bin com.employeems.Main
```
Run `build.bat` to start the application.
