package com.edu.neu.csye6200.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.edu.neu.csye6200.dao.StudentDao;
import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Fac.CourseDirectory;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

public class JF_MaintClass extends JInternalFrame {
    private JTable table_classes;
    private JTextArea area_classdesc = null;

    private DbUtil dbUtil = new DbUtil();
    private CourseDao courseDao = new CourseDao();
    private StudentDao studentDao = new StudentDao();

    private JTextField search_classname;
    private JTextField idTxt;
    private JTextField classNameTxt;

    public JF_MaintClass() {


        setClosable(true);
        setTitle("Maintain Class Info");
        setBounds(0, 0, GuiUtil.w - 30, GuiUtil.h - 90);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(null);
        jPanel1.setBounds(0, 0, GuiUtil.w - 30, 33);
        //jPanel1.setBackground(Color.red);
        JPanel jPanel2 = new JPanel();
        jPanel2.setBounds(0, 35, GuiUtil.w - 30, 200);
        //jPanel2.setBackground(Color.blue);
        JPanel jPanel3 = new JPanel();
        jPanel3.setBounds(0, 235, GuiUtil.w - 30, 300);
        //jPanel3.setBackground(Color.green);


        JScrollPane scrollPane = new JScrollPane();

        JLabel label = new JLabel("Class Name");
        label.setBounds(55, 5, 100, 30);
        jPanel1.add(label);

        search_classname = new JTextField();
        search_classname.setBounds(165, 5, 200, 30);
        jPanel1.add(search_classname);

        JButton button = new JButton("Search");
        button.setBounds(425, 5, 180, 30);
        jPanel1.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassSearch(e);
            }
        });
        button.setIcon(new ImageIcon(JF_MaintClass.class.getResource("/com/edu/neu/csye6200/images/search.png")));
        contentPane.add(jPanel1);


        table_classes = new JTable();
        table_classes.setBounds(0, 10, 600, 400);
        table_classes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseWhichRow(e);
            }
        });
        table_classes.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Class Name", "Class Description"}) {
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
        scrollPane.setBounds(0, 10, GuiUtil.w - 30, 200);
        jPanel2.add(scrollPane);

        this.initTable(new Course());

        jPanel3.setLayout(null);

        JLabel lb_ID = new JLabel("ID");
        lb_ID.setBounds(10, 10, 50, 30);
        idTxt = new JTextField();
        idTxt.setColumns(3);
        idTxt.setEditable(false);
        idTxt.setBounds(80, 10, 50, 30);
        jPanel3.add(idTxt);
        jPanel3.add(lb_ID);

        JLabel lb_classname = new JLabel("Class Name");
        lb_classname.setBounds(200, 10, 100, 30);
        classNameTxt = new JTextField();
        classNameTxt.setColumns(20);
        classNameTxt.setBounds(350, 10, 200, 30);
        jPanel3.add(lb_classname);
        jPanel3.add(classNameTxt);


        JLabel lb_classdes = new JLabel("Class Description");
        lb_classdes.setBounds(10, 60, 150, 30);
        area_classdesc = new JTextArea();
        area_classdesc.setColumns(10);
        area_classdesc.setBounds(180, 60, 380, 100);
        area_classdesc.setLineWrap(true);
        area_classdesc.setWrapStyleWord(true);
        jPanel3.add(lb_classdes);
        jPanel3.add(area_classdesc);

        JButton btn_update = new JButton("Update");
        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassUpdate(e);
            }
        });
        btn_update.setIcon(new ImageIcon(JF_MaintClass.class.getResource("/com/edu/neu/csye6200/images/modify.png")));

        JButton btn_delete = new JButton("Delete");
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassDelete(e);
            }
        });
        btn_delete.setIcon(new ImageIcon(JF_MaintClass.class.getResource("/com/edu/neu/csye6200/images/delete.png")));
        btn_delete.setBounds(100, 170, 150, 30);
        btn_update.setBounds(300, 170, 150, 30);
        jPanel3.add(btn_delete);
        jPanel3.add(btn_update);
        contentPane.add(jPanel2);
        contentPane.add(jPanel3);

    }


    private void ClassDelete(ActionEvent evt) {
        String id = idTxt.getText();
        if (StringUtil.isEmpty(id)) {
            //JOptionPane.showMessageDialog(null, "Select the record you want to delete");
            Object[] options = {"OK"};
            int result = JOptionPane.showOptionDialog(null, "Select the record you want to delete!", "Warning", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            return;
        }
        //int n=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the record?");
        Object[] options = {"Yes", "No"};
        int n = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the record??", "Warning", JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE, null, options, options[0]);

        if (n == 0) {
            Connection con = null;
            try {
                con = dbUtil.getCon();
                boolean flag = studentDao.existStudentByclassId(con, id);

                if (flag) {
                    System.out.println("do not exist this class");
                    // JOptionPane.showMessageDialog(null, "This Class has Student.\n Can't Delete");
                    Object[] options1 = {"OK"};
                    int result = JOptionPane.showOptionDialog(null, "This Class has Student.\n Can't Delete!", "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options1[0]);
                    return;
                }
                int deleteNum = courseDao.delete(con, id);
                if (deleteNum == 1) {
                    // JOptionPane.showMessageDialog(null, "Deleted successfully!");
                    Object[] options2 = {"OK"};
                    int result = JOptionPane.showOptionDialog(null, "Deleted successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options2[0]);
                    this.clear();
                    this.initTable(new Course());
                } else {
                    //JOptionPane.showMessageDialog(null, "Delete failed");
                    Object[] options3 = {"OK"};
                    int result = JOptionPane.showOptionDialog(null, "Delete failed!", "Warning", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, options, options3[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Delete failed");
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    private void ClassUpdate(ActionEvent evt) {
        String id = idTxt.getText();
        String className = classNameTxt.getText();
        String classDesc = area_classdesc.getText();
        if (StringUtil.isEmpty(id)) {
            // JOptionPane.showMessageDialog(null, "Select the record you want to modify");
            Object[] options = {"OK"};
            int result = JOptionPane.showOptionDialog(null, "Select the record you want to modify!", "Warning", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            return;
        }
        if (StringUtil.isEmpty(className)) {
            //JOptionPane.showMessageDialog(null, "Class names cannot be empty");
            Object[] options = {"OK"};
            int result = JOptionPane.showOptionDialog(null, "Class names cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            return;
        }
//        Course oldClass = CourseDirectory.getClassByName(className);
//        System.out.println("old" + oldClass);
//        CourseDirectory.removeClass(oldClass);
        //
        Course course = new Course(Integer.parseInt(id), className, classDesc);
//        CourseDirectory.getClasses().add(course);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            int modifyNum = courseDao.update(con, course);
            if (modifyNum == 1) {
                // JOptionPane.showMessageDialog(null, "Modification success");
                Object[] options = {"OK"};
                int result = JOptionPane.showOptionDialog(null, "Modification success!", "Warning", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                this.clear();
                this.initTable(new Course());
            } else {
                //JOptionPane.showMessageDialog(null, "Modification failed!");
                Object[] options = {"OK"};
                int result = JOptionPane.showOptionDialog(null, "Modification failed!", "Warning", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Modification failed!");
        } finally {
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void chooseWhichRow(MouseEvent evt) {
        int row = table_classes.getSelectedRow();
        idTxt.setText((String) table_classes.getValueAt(row, 0));
        classNameTxt.setText((String) table_classes.getValueAt(row, 1));
        area_classdesc.setText((String) table_classes.getValueAt(row, 2));
    }


    private void ClassSearch(ActionEvent evt) {
        String s_className = this.search_classname.getText();
        Course schoolClass = new Course();
        schoolClass.setClassName(s_className);
        this.initTable(schoolClass);

    }

    private void initTable(Course schoolClass) {
        DefaultTableModel dtm = (DefaultTableModel) table_classes.getModel();
        dtm.setRowCount(0);
        Connection con = null;
        try {
            con = dbUtil.getCon();
            ResultSet rs = courseDao.list(con, schoolClass);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("className"));
                v.add(rs.getString("classDesc"));
                dtm.addRow(v);
            }
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
    }


    private void clear() {
        this.idTxt.setText("");
        this.classNameTxt.setText("");
        this.area_classdesc.setText("");
    }
}