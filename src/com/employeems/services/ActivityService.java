package com.employeems.services;

import com.employeems.datastructures.EmployeeLinkedList;
import com.employeems.model.Employee;

public class ActivityService {
    private EmployeeLinkedList history;
    private int capacity;

    public ActivityService(int capacity) {
        this.capacity = capacity;
        history = new EmployeeLinkedList();
    }

    public void add(ActivityType type, String details) {
        // For simplicity, we encode activity as a string message
        // In a real design we would have a dedicated Activity class
    }
}
