package com.employeems.datastructures;

import com.employeems.model.EmployeeRequest;

public class RequestQueue {
    private Node<EmployeeRequest> front;
    private Node<EmployeeRequest> rear;
    private int size;

    public RequestQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    public void enqueue(EmployeeRequest req) {
        Node<EmployeeRequest> node = new Node<>(req);
        if (rear == null) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public EmployeeRequest dequeue() {
        if (front == null) return null;
        EmployeeRequest data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public EmployeeRequest peek() { return front != null ? front.data : null; }
    public boolean isEmpty() { return front == null; }
    public int size() { return size; }
}
