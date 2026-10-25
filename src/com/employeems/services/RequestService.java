package com.employeems.services;

import com.employeems.datastructures.RequestQueue;
import com.employeems.model.EmployeeRequest;

public class RequestService {
    private RequestQueue queue;

    public RequestService() {
        queue = new RequestQueue();
    }

    public void addRequest(EmployeeRequest req) {
        queue.enqueue(req);
    }

    public EmployeeRequest processNext() {
        return queue.dequeue();
    }

    public EmployeeRequest peek() { return queue.peek(); }
    public boolean isEmpty() { return queue.isEmpty(); }
    public int size() { return queue.size(); }
}
