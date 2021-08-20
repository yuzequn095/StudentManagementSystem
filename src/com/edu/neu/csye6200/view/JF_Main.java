package com.edu.neu.csye6200.view;

import com.edu.neu.csye6200.util.CsvAPI;
import com.edu.neu.csye6200.util.GuiUtil;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JF_Main extends JFrame {

    private JPanel contentPane;
    private JDesktopPane table = null;

    public JF_Main() {

        setTitle("Northeastern Information Management System");
        setBounds(GuiUtil.x, GuiUtil.y, GuiUtil.w, GuiUtil.h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        contentPane.setLayout(new BorderLayout(0, 0));
        table = new JDesktopPane();

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //menu of class

        JMenu JM_Class = new JMenu("Class");
        JM_Class.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/class.png")));
        menuBar.add(JM_Class);

        JMenuItem JI_Class_Edit = new JMenuItem("Add New Class");
        JI_Class_Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_AddNewClass jf_addNewClass = new JF_AddNewClass();
                jf_addNewClass.setVisible(true);
                table.add(jf_addNewClass);
            }
        });

        JI_Class_Edit.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/add.png")));
        JM_Class.add(JI_Class_Edit);

        JMenuItem JI_Class_Maintain = new JMenuItem("Maintain Class Info");
        JI_Class_Maintain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_MaintClass bookTypeManageInterFrm = new JF_MaintClass();
                bookTypeManageInterFrm.setVisible(true);

                table.add(bookTypeManageInterFrm);
            }
        });
        JI_Class_Maintain.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/maintain.png")));
        JM_Class.add(JI_Class_Maintain);
        //////////////////////////

        //menu of teacher

        JMenu JM_Teacher = new JMenu("Teacher");
        JM_Teacher.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/teachers.png")));
        menuBar.add(JM_Teacher);

        JMenuItem JI_Teacher_Add = new JMenuItem("Add New Teacher");
        JI_Teacher_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_AddTeacher teacherAddInterFrm = new JF_AddTeacher();
                teacherAddInterFrm.setVisible(true);

                table.add(teacherAddInterFrm);
            }
        });

        JI_Teacher_Add.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/add.png")));
        JM_Teacher.add(JI_Teacher_Add);

        JMenuItem JI_Teacher_Maintain = new JMenuItem("Maintain Teacher Info");
        JI_Teacher_Maintain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_MaintTeacher jfmaintteacher = new JF_MaintTeacher();
                jfmaintteacher.setVisible(true);

                table.add(jfmaintteacher);
            }
        });
        JI_Teacher_Maintain.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/maintain.png")));
        JM_Teacher.add(JI_Teacher_Maintain);

        //menu of student
        JMenu JM_Student = new JMenu("Student");
        JM_Student.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/student.png")));
        menuBar.add(JM_Student);

        JMenuItem JI_Student_Add = new JMenuItem("Add New Student");
        JI_Student_Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_AddStudent bookAddInterFrm = new JF_AddStudent();
                bookAddInterFrm.setVisible(true);
                table.add(bookAddInterFrm);
            }
        });

        JI_Student_Add.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/add.png")));
        JM_Student.add(JI_Student_Add);

        JMenuItem JI_Student_Maintain = new JMenuItem("Maintain Student Info");
        JI_Student_Maintain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_MaintStudent bookManageInterFrm = new JF_MaintStudent();
                bookManageInterFrm.setVisible(true);

                table.add(bookManageInterFrm);
            }
        });
        JI_Student_Maintain.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/maintain.png")));
        JM_Student.add(JI_Student_Maintain);

        //menu of about

        JMenu JM_Manage = new JMenu("Manage");
        JM_Manage.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/manage.png")));
        menuBar.add(JM_Manage);


        JMenuItem JI_Manage_About = new JMenuItem("Student Statistics");
        JI_Manage_About.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.removeAll();
                table.updateUI();
                JF_Static jf_static = new JF_Static();
                jf_static.setVisible(true);
                table.add(jf_static);
            }
        });

        JI_Manage_About.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/about.png")));
        JM_Manage.add(JI_Manage_About);

        JMenuItem JI_Manage_Export = new JMenuItem("Export Info to CSV");
        JI_Manage_Export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Export");
                //csv操作在此添加
                CsvAPI.runCSV();

            }
        });
        JI_Manage_Export.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/export.png")));
        JM_Manage.add(JI_Manage_Export);

        //menu of exit

        JMenuItem menuItem_end = new JMenuItem("Exit");
        menuItem_end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"Yes", "No"};
                int result = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Warning", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);

                if (result == 0) {
                    dispose();
                }

            }
        });
        menuItem_end.setIcon(new ImageIcon(JF_Main.class.getResource("/com/edu/neu/csye6200/images/exit.png")));
        menuBar.add(menuItem_end);


        this.setContentPane(contentPane);
        contentPane.add(table, BorderLayout.CENTER);

    }
}
