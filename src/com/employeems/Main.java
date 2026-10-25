package com.employeems;

import com.employeems.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        // Launch the Swing UI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
