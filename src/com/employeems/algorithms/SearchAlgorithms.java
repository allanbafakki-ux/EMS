package com.employeems.algorithms;

import com.employeems.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithms {
    /**
     * Linear search by employee name (case‑sensitive).  Returns first match.
     */
    public static Employee searchByName(Employee[] list, String name) {
        for (Employee e : list) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Linear search by department.
     */
    public static List<Employee> searchByDepartment(Employee[] list, String dept) {
        List<Employee> res = new ArrayList<>();
        for (Employee e : list) {
            if (e.getDepartment().equals(dept)) {
                res.add(e);
            }
        }
        return res;
    }

    /**
     * Linear search by designation.
     */
    public static List<Employee> searchByDesignation(Employee[] list, String desig) {
        List<Employee> res = new ArrayList<>();
        for (Employee e : list) {
            if (e.getDesignation().equals(desig)) {
                res.add(e);
            }
        }
        return res;
    }
}
