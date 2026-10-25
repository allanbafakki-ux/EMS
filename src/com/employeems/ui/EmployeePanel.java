package com.employeems.ui;

import com.employeems.model.Employee;
import com.employeems.services.EmployeeService;
import com.employeems.algorithms.SortAlgorithms;
import com.employeems.algorithms.SearchAlgorithms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EmployeePanel extends JPanel {
    private final EmployeeService service;
    private final DashboardPanel dashboard;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton addBtn, deleteBtn, undoBtn, refreshBtn, searchBtn, sortBtn;

    public EmployeePanel(EmployeeService service, DashboardPanel dashboard) {
        this.service = service;
        this.dashboard = dashboard;
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Gender", "Dept", "Desig", "Salary", "Phone", "Email", "DOJ"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        addBtn = new JButton("Add");
        deleteBtn = new JButton("Delete");
        undoBtn = new JButton("Undo Delete");
        refreshBtn = new JButton("Refresh");
        searchBtn = new JButton("Search");
        sortBtn = new JButton("Sort");
        btnPanel.add(addBtn);
        btnPanel.add(deleteBtn);
        btnPanel.add(undoBtn);
        btnPanel.add(refreshBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(sortBtn);
        add(btnPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> showAddDialog());
        deleteBtn.addActionListener(e -> deleteSelected());
        undoBtn.addActionListener(e -> undoDelete());
        refreshBtn.addActionListener(e -> loadTable());
        searchBtn.addActionListener(e -> showSearchDialog());
        sortBtn.addActionListener(e -> showSortDialog());

        loadTable();
        refreshDashboard();
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        Employee[] all = service.listAll();
        for (Employee e : all) {
            tableModel.addRow(new Object[]{
                    e.getEmployeeId(),
                    e.getName(),
                    e.getAge(),
                    e.getGender(),
                    e.getDepartment(),
                    e.getDesignation(),
                    e.getSalary(),
                    e.getPhone(),
                    e.getEmail(),
                    e.getDateOfJoining().format(DateTimeFormatter.ofPattern("dd-MM-yy"))
            });
        }
    }

    private void showAddDialog() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField gender = new JTextField();
        JTextField dept = new JTextField();
        JTextField desig = new JTextField();
        JTextField salary = new JTextField();
        JTextField phone = new JTextField();
        JTextField email = new JTextField();
        JTextField doj = new JTextField(); // DD-MM-YY

        Object[] message = {
                "ID", id,
                "Name", name,
                "Age", age,
                "Gender", gender,
                "Department", dept,
                "Designation", desig,
                "Salary", salary,
                "Phone", phone,
                "Email", email,
                "Date of Joining", doj
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                LocalDate dojDate;
                try {
                    dojDate = LocalDate.parse(doj.getText(), DateTimeFormatter.ofPattern("dd-MM-yy"));
                } catch (DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(this, "Invalid date. Please use DD-MM-YY (e.g., 01-06-26)", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Employee e = new Employee(
                        id.getText(),
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        gender.getText(),
                        dept.getText(),
                        desig.getText(),
                        Double.parseDouble(salary.getText()),
                        phone.getText(),
                        email.getText(),
                        dojDate
                );

                service.addEmployee(e);
                loadTable();
                service.save();
                refreshDashboard();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

    private void deleteSelected() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        String id = (String) tableModel.getValueAt(row, 0);
        if (service.deleteEmployee(id)) {
            loadTable();
            service.save();
            refreshDashboard();
        }
    }

    private void undoDelete() {
        if (service.undoDelete() != null) {
            loadTable();
            service.save();
            refreshDashboard();
        }
    }

    private void showSearchDialog() {
        String id = JOptionPane.showInputDialog(this, "Enter Employee ID to search:");
        if (id != null) {
            Employee e = service.getById(id);
            if (e != null) {
                String info = "ID: " + e.getEmployeeId() + "\nName: " + e.getName();
                JOptionPane.showMessageDialog(this, info);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
            }
        }
    }

    private void showSortDialog() {
        Object[] options = {"ID", "Name", "Salary", "Department"};
        int choice = JOptionPane.showOptionDialog(this, "Sort by", "Sort", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice >= 0) {
            Employee[] arr = service.listAll();
            switch (choice) {
                case 0: SortAlgorithms.bubbleSort(arr, SortAlgorithms.byId()); break;
                case 1: SortAlgorithms.bubbleSort(arr, SortAlgorithms.byName()); break;
                case 2: SortAlgorithms.bubbleSort(arr, SortAlgorithms.bySalary()); break;
                case 3: SortAlgorithms.bubbleSort(arr, SortAlgorithms.byDepartment()); break;
            }
            tableModel.setRowCount(0);
            for (Employee e : arr) {
                tableModel.addRow(new Object[]{
                        e.getEmployeeId(),
                        e.getName(),
                        e.getAge(),
                        e.getGender(),
                        e.getDepartment(),
                        e.getDesignation(),
                        e.getSalary(),
                        e.getPhone(),
                        e.getEmail(),
                        e.getDateOfJoining().format(DateTimeFormatter.ofPattern("dd-MM-yy"))
                });
            }
        }
    }

    private void refreshDashboard() {
        if (dashboard != null) {
            dashboard.updateStats(
                    service.getTotalEmployees(),
                    service.getUniqueDepartmentCount(),
                    service.getAverageSalary()
            );
        }
    }
}