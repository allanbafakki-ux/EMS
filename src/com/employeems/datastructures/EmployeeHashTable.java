package com.employeems.datastructures;

import com.employeems.model.Employee;

import java.util.NoSuchElementException;

public class EmployeeHashTable {
    private static class HashNode {
        String key;
        Employee value;
        HashNode next;

        HashNode(String key, Employee value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashNode[] table;
    private int capacity;
    private int size;

    public EmployeeHashTable(int capacity) {
        this.capacity = capacity;
        table = new HashNode[capacity];
        this.size = 0;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, Employee value) {
        int index = hash(key);
        HashNode node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value; // update
                return;
            }
            node = node.next;
        }
        // insert at head
        HashNode newNode = new HashNode(key, value);
        newNode.next = table[index];
        table[index] = newNode;
        size++;
    }

    public Employee get(String key) {
        int index = hash(key);
        HashNode node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public boolean remove(String key) {
        int index = hash(key);
        HashNode node = table[index];
        HashNode prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    table[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return true;
            }
            prev = node;
            node = node.next;
        }
        return false;
    }

    public int size() { return size; }
    public int capacity() { return capacity; }
}
