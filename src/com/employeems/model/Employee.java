package com.employeems.model;

import java.time.LocalDate;

public class Employee {
    private String employeeId;
    private String name;
    private int age;
    private String gender;
    private String department;
    private String designation;
    private double salary;
    private String phone;
    private String email;
    private LocalDate dateOfJoining;

    public Employee(String employeeId, String name, int age, String gender, String department,
            String designation, double salary, String phone, String email, LocalDate dateOfJoining) {
        this.employeeId = employeeId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.dateOfJoining = dateOfJoining;
    }

    // Basic getters and setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + "]";
    }
}
