package com.mobinyousefi.gradingsystem;

import com.mobinyousefi.gradingsystem.ui.MainFrame;

import javax.swing.*;

/**
 * Project: Grading System
 * File: App.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Application entry point. Boots the Swing UI on the EDT.
 */
public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Optional: use system look & feel for a more native appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
