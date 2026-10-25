# Employee Record Management System

A simple desktop application written in **Java Swing** that demonstrates a combination of
- Object‑Oriented Programming concepts (inheritance, abstraction, polymorphism, interfaces, exception handling)
- Core Data‑Structures implemented **from scratch** (Linked List, Stack, Queue, Hash Table)
- Classic Algorithms – linear search, hash lookup, bubble/selection sort
- File Persistence using plain CSV

The project fulfills the requirements of the interdisciplinary exam for the courses 24CCT201 (DSA) and 24CCT203 (OOP) at Amal Jyothi College of Engineering.

## Quick start
```
# Compile
mkdir bin
javac -d bin -sourcepath src src/com/employeems/Main.java
# Run
java -cp bin com.employeems.Main
```

## Features
- **Employee CRUD** – add/edit/delete/undo delete (through a `Stack`).
- **Search** – by ID (Hash Table), name, department, designation (linear).
- **Sort** – by ID, name, salary, department (bubble‑sort in‑place).
- **Dashboard** – see total employees, number of departments, average salary.
- **Request module** – FIFO processing of leaf/HR requests (custom `Queue`).
- **Activity history** – (placeholder) can be extended from custom `LinkedList`.
- **Persistence** – data written to `data/employees.csv` on every update.

## Project layout
```
EmployeeRecordManagementSystem/
├─ src/com/employeems/    (Java source)
├─ data/                   (CSV persistence)
├─ docs/                   (report, UML, guide)
├─ build/                  (compiled classes)
├─ README.md
└─ Main.java
```

## Documentation
See the `docs/` folder for:
- `UML.md` – Class diagram (Mermaid)
- `report.md` – Full 5‑10 page report
- `guide.md` – Deployment, usage, and testing guide
- `algorithms.md` – Pseudocode and analysis

## Team
- Student 1: OOP + Linked List
- Student 2: Requests + Queue + Hash Table
- Student 3: Undo + Stack + Sorting

## License
MIT – feel free to adapt.
