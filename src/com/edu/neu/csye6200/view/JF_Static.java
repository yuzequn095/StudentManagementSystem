package com.edu.neu.csye6200.view;

import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.dao.StudentDao;
import com.edu.neu.csye6200.dao.TeacherDao;
import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StatisticsUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class JF_Static extends JInternalFrame {
    private JTable table_classes;
    private JTextArea area_classdesc = null;

    private DbUtil dbUtil = new DbUtil();
    private CourseDao courseDao = new CourseDao();
    private StudentDao studentDao = new StudentDao();
    private TeacherDao teacherDao = new TeacherDao();
    private List<Person> allPerson = new ArrayList<>();


    public JF_Static() {
        Connection con = null;
        try {
            con = dbUtil.getCon();
            allPerson.addAll(studentDao.studentList(con));
            allPerson.addAll(teacherDao.teacherList(con));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        setClosable(true);
        setTitle("Static");
        setBounds(0, 0, GuiUtil.w - 30, GuiUtil.h - 90);
        JLabel jLabel = new JLabel("Sorted By:");
        jLabel.setBounds(5, 5, 150, 30);
        contentPane.add(jLabel);

        new StatisticsUtil().studentDisplay(allPerson);

        //创建并添加一个button
        JButton btn_name = new JButton("Name");
        btn_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //初始化table 按name顺序 在这里调初始化table的函数！！！！！！！
                System.out.println("press name btn");
                StatisticsUtil.sortedByName(allPerson);
                initTable();
            }
        });
        btn_name.setBounds(155, 5, 150, 30);//改button大小！！！！！！x y是水平距离 w h是btn的长宽！
        contentPane.add(btn_name);

        //创建并添加一个button
        JButton btn_sex = new JButton("SN");
        btn_sex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //初始化table 按name顺序 在这里调初始化table的函数！！！！！！！
                System.out.println("press sn btn");
                StatisticsUtil.sortedBySN(allPerson);
                initTable();
            }
        });
        btn_sex.setBounds(355, 5, 150, 30);
        contentPane.add(btn_sex);


        JScrollPane scrollPane = new JScrollPane();
        table_classes = new JTable();
        table_classes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Name", "Gender"}) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_classes.getColumnModel().getColumn(1).setPreferredWidth(110);
        table_classes.getColumnModel().getColumn(2).setPreferredWidth(123);
        scrollPane.setViewportView(table_classes);
        scrollPane.setBounds(0, 40, GuiUtil.w - 30, 200);
        contentPane.add(scrollPane);

        //初始化表格
        initTable();


    }


    private void initTable() {
        DefaultTableModel dtm = (DefaultTableModel) table_classes.getModel();
        dtm.setRowCount(0);

        for (Person temp : allPerson) {
            Vector v = new Vector();
            v.add(temp.getSn());
            v.add(temp.getName());
            v.add(temp.getSex());
            dtm.addRow(v);
        }
    }
}