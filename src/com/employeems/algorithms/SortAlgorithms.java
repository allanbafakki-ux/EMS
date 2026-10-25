package com.employeems.algorithms;

import com.employeems.model.Employee;

public class SortAlgorithms {
    /**
     * Bubble sort implementation. Comparator decides the order.
     * @return sorted array
     */
    public static void bubbleSort(Employee[] arr, java.util.Comparator<Employee> cmp) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (cmp.compare(arr[j], arr[j + 1]) > 0) {
                    Employee temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Convenience comparators
    public static java.util.Comparator<Employee> byId() {
        return (a, b) -> a.getEmployeeId().compareTo(b.getEmployeeId());
    }
    public static java.util.Comparator<Employee> byName() {
        return (a, b) -> a.getName().compareTo(b.getName());
    }
    public static java.util.Comparator<Employee> bySalary() {
        return (a, b) -> Double.compare(a.getSalary(), b.getSalary());
    }
    public static java.util.Comparator<Employee> byDepartment() {
        return (a, b) -> a.getDepartment().compareTo(b.getDepartment());
    }
}
