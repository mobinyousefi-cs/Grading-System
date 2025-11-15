package com.mobinyousefi.gradingsystem.ui;

import com.mobinyousefi.gradingsystem.dao.MarkDao;
import com.mobinyousefi.gradingsystem.dao.StudentDao;
import com.mobinyousefi.gradingsystem.dao.SubjectDao;
import com.mobinyousefi.gradingsystem.model.Student;
import com.mobinyousefi.gradingsystem.model.StudentRanking;
import com.mobinyousefi.gradingsystem.model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Project: Grading System
 * File: MainFrame.java
 * Author: Mobin Yousefi (GitHub: github.com/mobinyousefi-cs)
 * Description: Main Swing window containing student, subject and ranking management.
 */
public class MainFrame extends JFrame {

    private final StudentDao studentDao = new StudentDao();
    private final SubjectDao subjectDao = new SubjectDao();
    private final MarkDao markDao = new MarkDao();

    private DefaultTableModel studentsModel;
    private DefaultTableModel subjectsModel;
    private DefaultTableModel rankingsModel;

    private JComboBox<Student> studentCombo;
    private JComboBox<Subject> subjectCombo;
    private JTextField marksField;

    public MainFrame() {
        super("Grading System - Mobin Yousefi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Students", buildStudentsPanel());
        tabs.addTab("Subjects", buildSubjectsPanel());
        tabs.addTab("Marks & Ranking", buildMarksPanel());

        add(tabs, BorderLayout.CENTER);
        reloadAllTables();
    }

    private JPanel buildStudentsPanel() {
        JPanel root = new JPanel(new BorderLayout());

        // Form
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField nameField = new JTextField(20);
        JTextField regField = new JTextField(12);
        JTextField deptField = new JTextField(15);

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Registration #:"), gbc);
        gbc.gridx = 1; form.add(regField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; form.add(deptField, gbc);

        JButton addBtn = new JButton("Add Student");
        addBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String reg = regField.getText().trim();
            String dept = deptField.getText().trim();

            if (name.isEmpty() || reg.isEmpty() || dept.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                studentDao.insert(new Student(name, reg, dept));
                nameField.setText("");
                regField.setText("");
                deptField.setText("");
                reloadStudents();
                reloadCombos();
            } catch (SQLException ex) {
                showError("Could not insert student", ex);
            }
        });

        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        form.add(addBtn, gbc);

        root.add(form, BorderLayout.NORTH);

        // Table
        studentsModel = new DefaultTableModel(new Object[]{"ID", "Name", "Reg #", "Department"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(studentsModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        return root;
    }

    private JPanel buildSubjectsPanel() {
        JPanel root = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField codeField = new JTextField(10);
        JTextField nameField = new JTextField(20);
        JSpinner creditsSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Code:"), gbc);
        gbc.gridx = 1; form.add(codeField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; form.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Credits:"), gbc);
        gbc.gridx = 1; form.add(creditsSpinner, gbc);

        JButton addBtn = new JButton("Add Subject");
        addBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            int credits = (int) creditsSpinner.getValue();

            if (code.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Code and Name are required.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                subjectDao.insert(new Subject(code, name, credits));
                codeField.setText("");
                nameField.setText("");
                creditsSpinner.setValue(3);
                reloadSubjects();
                reloadCombos();
            } catch (SQLException ex) {
                showError("Could not insert subject", ex);
            }
        });

        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        form.add(addBtn, gbc);

        root.add(form, BorderLayout.NORTH);

        subjectsModel = new DefaultTableModel(new Object[]{"ID", "Code", "Name", "Credits"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(subjectsModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        return root;
    }

    private JPanel buildMarksPanel() {
        JPanel root = new JPanel(new BorderLayout());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        studentCombo = new JComboBox<>();
        subjectCombo = new JComboBox<>();
        marksField = new JTextField(6);

        gbc.gridx = 0; gbc.gridy = 0; form.add(new JLabel("Student:"), gbc);
        gbc.gridx = 1; form.add(studentCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; form.add(new JLabel("Subject:"), gbc);
        gbc.gridx = 1; form.add(subjectCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 2; form.add(new JLabel("Marks (0-100):"), gbc);
        gbc.gridx = 1; form.add(marksField, gbc);

        JButton addBtn = new JButton("Save Mark");
        addBtn.addActionListener(e -> saveMark());

        gbc.gridx = 1; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        form.add(addBtn, gbc);

        root.add(form, BorderLayout.NORTH);

        rankingsModel = new DefaultTableModel(new Object[]{"Rank", "Name", "Reg #", "Total", "Average"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(rankingsModel);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        return root;
    }

    private void saveMark() {
        Student student = (Student) studentCombo.getSelectedItem();
        Subject subject = (Subject) subjectCombo.getSelectedItem();

        if (student == null || subject == null) {
            JOptionPane.showMessageDialog(this, "Please select a student and subject.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double marks;
        try {
            marks = Double.parseDouble(marksField.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Marks must be a numeric value.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (marks < 0 || marks > 100) {
            JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            markDao.insert(student.getId(), subject.getId(), marks);
            marksField.setText("");
            reloadRankings();
        } catch (SQLException ex) {
            showError("Could not save mark", ex);
        }
    }

    private void reloadAllTables() {
        reloadStudents();
        reloadSubjects();
        reloadRankings();
        reloadCombos();
    }

    private void reloadStudents() {
        try {
            List<Student> students = studentDao.findAll();
            studentsModel.setRowCount(0);
            for (Student s : students) {
                studentsModel.addRow(new Object[]{s.getId(), s.getName(), s.getRegistrationNumber(), s.getDepartment()});
            }
        } catch (SQLException ex) {
            showError("Could not load students", ex);
        }
    }

    private void reloadSubjects() {
        try {
            List<Subject> subjects = subjectDao.findAll();
            subjectsModel.setRowCount(0);
            for (Subject s : subjects) {
                subjectsModel.addRow(new Object[]{s.getId(), s.getCode(), s.getName(), s.getCredits()});
            }
        } catch (SQLException ex) {
            showError("Could not load subjects", ex);
        }
    }

    private void reloadRankings() {
        try {
            List<StudentRanking> rankings = markDao.fetchRankings();
            rankingsModel.setRowCount(0);
            for (StudentRanking r : rankings) {
                rankingsModel.addRow(new Object[]{r.getRank(), r.getStudentName(), r.getRegistrationNumber(),
                        String.format("%.2f", r.getTotalMarks()), String.format("%.2f", r.getAverageMarks())});
            }
        } catch (SQLException ex) {
            showError("Could not load rankings", ex);
        }
    }

    private void reloadCombos() {
        try {
            List<Student> students = studentDao.findAll();
            studentCombo.removeAllItems();
            for (Student s : students) {
                studentCombo.addItem(s);
            }

            List<Subject> subjects = subjectDao.findAll();
            subjectCombo.removeAllItems();
            for (Subject s : subjects) {
                subjectCombo.addItem(s);
            }
        } catch (SQLException ex) {
            showError("Could not reload combo data", ex);
        }
    }

    private void showError(String message, Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, message + "\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
