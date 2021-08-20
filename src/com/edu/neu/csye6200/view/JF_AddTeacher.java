package com.edu.neu.csye6200.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.dao.TeacherDao;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.model.Fac.TeacherFac;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

public class JF_AddTeacher extends JInternalFrame {
	private JTextField teacherNameTxt;
	private JTextField tidTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox JCB_Courses;
	private JComboBox JCB_Dept;
	private JTextArea addressTxt;
	private JRadioButton manJrb;
	private JRadioButton femaleJrb;
	
	private DbUtil dbUtil=new DbUtil();
	private CourseDao schoolClassDao=new CourseDao();
	private TeacherDao teacherDao=new TeacherDao();

	

	public JF_AddTeacher() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		setClosable(true);

		setTitle("Add New Teacher");
		setBounds(0, 0, GuiUtil.w-30, GuiUtil.h-90);
		
		JLabel label = new JLabel("Name");
		label.setBounds(30,35,70,30);
		
		teacherNameTxt = new JTextField();
		teacherNameTxt.setColumns(10);
		teacherNameTxt.setBounds(150,35,200,30);

		JLabel label_1 = new JLabel("TID");
		label_1.setBounds(400,35,80,30);

		tidTxt = new JTextField();
		tidTxt.setColumns(10);
		tidTxt.setBounds(480,35,100,30);

		contentPane.add(label);
		contentPane.add(teacherNameTxt);
		contentPane.add(label_1);
		contentPane.add(tidTxt);

		JLabel label_2 = new JLabel("Sex");
		label_2.setBounds(30,95,50,30);

		manJrb = new JRadioButton("Male");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		manJrb.setBounds(140,95,80,30);

		femaleJrb = new JRadioButton("Female");
		buttonGroup.add(femaleJrb);
		femaleJrb.setBounds(240,95,100,30);

		JLabel label_3 = new JLabel("Dept");
		label_3.setBounds(400,95,80,30);
		JCB_Dept=new JComboBox();
		deptItemInit();
		JCB_Dept.setBounds(480,95,100,30);
		contentPane.add(label_3);
		contentPane.add(JCB_Dept);

		//deptTxt = new JTextField();
		//deptTxt.setColumns(10);
		
		JLabel label_4 = new JLabel("Home address");
		label_4.setBounds(30,215,120,30);
		contentPane.add(label_4);
		addressTxt = new JTextArea();
		addressTxt.setBounds(150,215,200,60);
		contentPane.add(addressTxt);
		addressTxt.setLineWrap(true);
		addressTxt.setWrapStyleWord(true);
		JLabel label_5 = new JLabel("Class");
		label_5.setBounds(30,155,80,30);
		contentPane.add(label_5);
	    JCB_Courses = new JComboBox();
		coursesItemInit();
		JCB_Courses.setBounds(150,155,200,30);
		contentPane.add(JCB_Courses);


		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teacherAddActionPerformed(e);
			}
		});
		button.setIcon(new ImageIcon(JF_AddStudent.class.getResource("/com/edu/neu/csye6200/images/add.png")));
		button.setBounds(40,400,100,30);
		contentPane.add(button);
		JButton button_1 = new JButton("Clear");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		button_1.setIcon(new ImageIcon(JF_AddStudent.class.getResource("/com/edu/neu/csye6200/images/clear.png")));
		button_1.setBounds(440,400,100,30);
		contentPane.add(button_1);
	}

	private void resetValueActionPerformed(ActionEvent e) {
		this.clear();
	}

	private void teacherAddActionPerformed(ActionEvent evt) {
		String name=this.teacherNameTxt.getText();
		String teacherNumber=this.tidTxt.getText();
		String address=this.addressTxt.getText();
		
		if(StringUtil.isEmpty(name)){
			JOptionPane.showMessageDialog(null, "Teacher names cannot be empty");
			return;
		}
		
		if(StringUtil.isEmpty(teacherNumber)){
			JOptionPane.showMessageDialog(null, "Teacher ID cannot be empty");
			return;
		}

		
		String sex="";
		if(manJrb.isSelected()){
			sex="male";
		}else if(femaleJrb.isSelected()){
			sex="Female";
		}
		
		Course classType=(Course) JCB_Courses.getSelectedItem();
		int classId=classType.getId();
		String selectedDept= (String) JCB_Dept.getSelectedItem();
		
		Teacher teacher = TeacherFac.addNewTeacher(name, teacherNumber, sex, selectedDept , classId, address);
		System.out.println("There are " + TeacherFac.getTeachers().size() + "teachers");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=teacherDao.add(con, teacher);
			if(addNum==1){
				//JOptionPane.showMessageDialog(null, "Teacher added successfully");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Teacher added successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				clear();
			}else{
				//JOptionPane.showMessageDialog(null, "Teacher added failed");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Teacher added successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Teacher added failed");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	private void clear(){
		this.teacherNameTxt.setText("");
		this.tidTxt.setText("");
		this.manJrb.setSelected(true);
		this.addressTxt.setText("");
		if(this.JCB_Courses.getItemCount()>0){
			this.JCB_Courses.setSelectedIndex(0);
		}
		if(this.JCB_Dept.getItemCount()>0){
			this.JCB_Dept.setSelectedIndex(0);
		}
	}

	private void coursesItemInit(){
		Connection con=null;
		Course course=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=schoolClassDao.list(con, new Course());
			while(rs.next()){
				course=new Course();
				course.setId(rs.getInt("id"));
				course.setClassName(rs.getString("className"));
				course.setCalssDesc("");
				this.JCB_Courses.addItem(course);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	private void deptItemInit(){
		Connection con=null;
		Course course=null;
		try{
			con=dbUtil.getCon();
			String[] depts=GuiUtil.depts;
			for(int i=0;i<depts.length;i++){
				JCB_Dept.addItem(depts[i]);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
	}
}
