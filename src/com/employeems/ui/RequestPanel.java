package com.employeems.ui;

import com.employeems.services.RequestService;
import com.employeems.model.EmployeeRequest;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class RequestPanel extends JPanel {
    private RequestService service;
    private JTextArea area;

    public RequestPanel(RequestService service) {
        this.service = service;
        setLayout(new BorderLayout());
        area = new JTextArea();
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
        JButton addBtn = new JButton("Add Request");
        JButton procBtn = new JButton("Process Next");
        JPanel btnPanel = new JPanel();
        btnPanel.add(addBtn);
        btnPanel.add(procBtn);
        add(btnPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> showAddDialog());
        procBtn.addActionListener(e -> processNext());
        refresh();
    }

    private void showAddDialog() {
        JTextField id = new JTextField();
        JTextField type = new JTextField();
        JTextField desc = new JTextField();
        Object[] msg = {"Employee ID", id, "Type", type, "Description", desc};
        if (JOptionPane.showConfirmDialog(this, msg, "Add Request", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            EmployeeRequest req = new EmployeeRequest(id.getText(), type.getText(), desc.getText(), LocalDate.now());
            service.addRequest(req);
            refresh();
        }
    }

    private void processNext() {
        EmployeeRequest req = service.processNext();
        if (req != null) {
            JOptionPane.showMessageDialog(this, "Processed request for Employee ID: " + req.getEmployeeId());
            refresh();
        } else {
            JOptionPane.showMessageDialog(this, "No pending requests.");
        }
    }

    private void refresh() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pending Requests: ").append(service.size()).append("\n");
        sb.append("-- Queue --\n");
        // For now just show count; can extend.
        area.setText(sb.toString());
    }
}
