package com.employeems.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class DashboardPanel extends JPanel {
    private JLabel totalLabel;
    private JLabel departmentLabel;
    private JLabel averageSalaryLabel;

    public DashboardPanel() {
        setLayout(new GridLayout(3, 1));
        totalLabel = new JLabel("Total Employees: 0");
        departmentLabel = new JLabel("Departments: 0");
        averageSalaryLabel = new JLabel("Average Salary: 0");
        add(totalLabel);
        add(departmentLabel);
        add(averageSalaryLabel);
    }

    public void updateStats(int total, int deptCount, double avgSalary) {
        totalLabel.setText("Total Employees: " + total);
        departmentLabel.setText("Departments: " + deptCount);
        averageSalaryLabel.setText(String.format("Average Salary: %.2f", avgSalary));
    }
}
