package com.employeems.ui;

import com.employeems.services.EmployeeService;
import com.employeems.services.RequestService;
import com.employeems.ui.DashboardPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private EmployeeService employeeService;
    private RequestService requestService;

    public MainFrame() {
        super("Employee Record Management System");
        employeeService = new EmployeeService();
        requestService = new RequestService();

        DashboardPanel dashboard = new DashboardPanel();

        JTabbedPane tabbed = new JTabbedPane();
        tabbed.addTab("Dashboard", dashboard);
        tabbed.addTab("Employees", new EmployeePanel(employeeService, dashboard));
        tabbed.addTab("Requests", new RequestPanel(requestService));
        // Other tabs (Activity, About) would be added here.
        add(tabbed);

        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
