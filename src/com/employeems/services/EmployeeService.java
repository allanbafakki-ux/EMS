package com.employeems.services;

import com.employeems.datastructures.EmployeeHashTable;
import com.employeems.datastructures.EmployeeLinkedList;
import com.employeems.datastructures.EmployeeStack;
import com.employeems.model.Employee;
import com.employeems.persistence.FileManager;

import java.io.IOException;

public class EmployeeService {
    private EmployeeLinkedList linkedList;
    private EmployeeHashTable hashTable;
    private EmployeeStack deleteStack;

    public EmployeeService() { 
        linkedList = new EmployeeLinkedList();
        hashTable = new EmployeeHashTable(997); // prime capacity
        deleteStack = new EmployeeStack();
        load();
    }

    private void load() {
        try {
            linkedList = FileManager.loadEmployees();
            // rebuild hash table
            Employee[] arr = linkedList.toArray();
            for (Employee e : arr) {
                hashTable.put(e.getEmployeeId(), e);
            }
        } catch (IOException e) {
            System.err.println("Failed to load employees: " + e.getMessage());
        }
    }

    public void addEmployee(Employee e) {
        if (hashTable.containsKey(e.getEmployeeId())) {
            throw new IllegalArgumentException("Duplicate Employee ID");
        }
        linkedList.insert(e);
        hashTable.put(e.getEmployeeId(), e);
    }

    public Employee getById(String id) {
        return hashTable.get(id);
    }

    public boolean deleteEmployee(String id) {
        Employee e = hashTable.get(id);
        if (e == null) return false;
        boolean deleted = linkedList.deleteById(id);
        if (deleted) {
            deleteStack.push(e);
            hashTable.remove(id);
            return true;
        }
        return false;
    }

    public Employee undoDelete() {
        Employee e = deleteStack.pop();
        if (e != null) {
            addEmployee(e);
        }
        return e;
    }

    public Employee[] listAll() {
        return linkedList.toArray();
    }

    public void save() {
        try {
            FileManager.saveEmployees(linkedList);
        } catch (IOException e) {
            System.err.println("Failed to save employees: " + e.getMessage());
        }
    }

    /** Returns the number of employees currently stored. */
    public int getTotalEmployees() {
        return listAll().length;
    }

    /** Returns the count of distinct departments. */
    public int getUniqueDepartmentCount() {
        java.util.Set<String> deptSet = new java.util.HashSet<>();
        for (Employee e : listAll()) {
            deptSet.add(e.getDepartment());
        }
        return deptSet.size();
    }

    /** Returns the average salary (0.0 if there are no employees). */
    public double getAverageSalary() {
        Employee[] arr = listAll();
        if (arr.length == 0) return 0.0;
        double sum = 0.0;
        for (Employee e : arr) {
            sum += e.getSalary();
        }
        return sum / arr.length;
    }
}
