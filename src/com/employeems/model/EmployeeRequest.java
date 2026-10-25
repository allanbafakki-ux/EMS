package com.employeems.model;

import java.time.LocalDate;

public class EmployeeRequest {
    private String employeeId;
    private String requestType;
    private String description;
    private LocalDate date;

    public EmployeeRequest(String employeeId, String requestType, String description, LocalDate date) {
        this.employeeId = employeeId;
        this.requestType = requestType;
        this.description = description;
        this.date = date;
    }

    public String getEmployeeId() { return employeeId; }
    public String getRequestType() { return requestType; }
    public String getDescription() { return description; }
    public LocalDate getDate() { return date; }
}
