package com.employeems.datastructures;

import com.employeems.model.Employee;
import java.util.NoSuchElementException;

public class EmployeeLinkedList {
    private Node<Employee> head;
    private int size;

    public EmployeeLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public void insert(Employee employee) {
        Node<Employee> newNode = new Node<>(employee);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public Employee searchById(String id) {
        Node<Employee> current = head;
        while (current != null) {
            if (current.data.getEmployeeId().equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean deleteById(String id) {
        Node<Employee> current = head;
        Node<Employee> prev = null;
        while (current != null) {
            if (current.data.getEmployeeId().equals(id)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public Employee[] toArray() {
        Employee[] arr = new Employee[size];
        Node<Employee> current = head;
        int i = 0;
        while (current != null) {
            arr[i++] = current.data;
            current = current.next;
        }
        return arr;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
