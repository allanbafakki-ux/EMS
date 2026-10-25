package com.employeems.persistence;

import com.employeems.model.Employee;
import com.employeems.datastructures.EmployeeLinkedList;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "data/employees.csv";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy");

    public static void saveEmployees(EmployeeLinkedList list) throws IOException {
        // Ensure directory exists
        new java.io.File(FILE_PATH).getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Employee[] arr = list.toArray();
            for (Employee e : arr) {
                writer.write(toCsv(e));
                writer.newLine();
            }
        }
    }

    public static EmployeeLinkedList loadEmployees() throws IOException {
        EmployeeLinkedList list = new EmployeeLinkedList();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return list; // empty list
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Employee e = fromCsv(line);
                if (e != null) {
                    list.insert(e);
                }
            }
        }
        return list;
    }

    private static String toCsv(Employee e) {
        return String.join(",",
                e.getEmployeeId(),
                e.getName(),
                String.valueOf(e.getAge()),
                e.getGender(),
                e.getDepartment(),
                e.getDesignation(),
                String.valueOf(e.getSalary()),
                e.getPhone(),
                e.getEmail(),
                e.getDateOfJoining().format(FORMATTER));
    }

    private static Employee fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length != 10) return null;
        try {
            return new Employee(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    parts[3],
                    parts[4],
                    parts[5],
                    Double.parseDouble(parts[6]),
                    parts[7],
                    parts[8],
                    LocalDate.parse(parts[9], FORMATTER));
        } catch (Exception e) {
            return null;
        }
    }
}
