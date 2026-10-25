package com.employeems.datastructures;

import com.employeems.model.Employee;

public class EmployeeQueue {
    private Node<Employee> front;
    private Node<Employee> rear;
    private int size;

    public EmployeeQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(Employee emp) {
        Node<Employee> node = new Node<>(emp);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public Employee dequeue() {
        if (front == null) return null;
        Employee data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public Employee peek() { return front != null ? front.data : null; }
    public boolean isEmpty() { return front == null; }
    public int size() { return size; }
}
