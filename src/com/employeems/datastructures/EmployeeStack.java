package com.employeems.datastructures;

import com.employeems.model.Employee;

public class EmployeeStack {
    private Node<Employee> top;
    private int size;

    public EmployeeStack() {
        top = null;
        size = 0;
    }

    public void push(Employee emp) {
        Node<Employee> node = new Node<>(emp);
        node.next = top;
        top = node;
        size++;
    }

    public Employee pop() {
        if (top == null) return null;
        Employee data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public Employee peek() { return top != null ? top.data : null; }
    public boolean isEmpty() { return top == null; }
    public int size() { return size; }
}
