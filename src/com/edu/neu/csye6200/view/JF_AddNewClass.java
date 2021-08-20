package com.edu.neu.csye6200.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.*;

import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Fac.CourseDirectory;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

public class JF_AddNewClass extends JInternalFrame {
	
	private JTextField txt_classname;
	private JTextArea txt_classdesp;
	
	private DbUtil dbUtil=new DbUtil();
	private CourseDao schoolClassDao=new CourseDao();


	public JF_AddNewClass() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		setClosable(true);
		setTitle("Add One New Class");
		setBounds(0, 0, GuiUtil.w-30, GuiUtil.h-90);
		
		JLabel lb_name= new JLabel("Class Name");
		lb_name.setBounds(80,75,200,20);
		txt_classname = new JTextField();
		txt_classname.setBounds(300,75,250,30);
		JLabel lb_des = new JLabel("Class Description");
		lb_des.setBounds(80,175,200,20);
		txt_classdesp = new JTextArea();
		txt_classdesp.setBounds(300,175,250,150);

		JButton btn_add = new JButton("ADD");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddClass(e);
			}
		});
		btn_add.setIcon(new ImageIcon(JF_AddNewClass.class.getResource("/com/edu/neu/csye6200/images/add.png")));
		btn_add.setBounds(80,375,100,30);
		
		JButton btn_clear = new JButton("Clear");
		btn_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear(e);
			}
		});


		btn_clear.setIcon(new ImageIcon(JF_AddNewClass.class.getResource("/com/edu/neu/csye6200/images/clear.png")));
		btn_clear.setBounds(480,375,100,30);

		contentPane.add(lb_name);
		contentPane.add(lb_des);
		contentPane.add(txt_classname);
		contentPane.add(txt_classdesp);
		contentPane.add(btn_add);
		contentPane.add(btn_clear);
		txt_classdesp.setLineWrap(true);
		txt_classdesp.setWrapStyleWord(true);


	}
	

	private void AddClass(ActionEvent evt) {
		String className=this.txt_classname.getText();
		String classDesc=this.txt_classdesp.getText();
		if(StringUtil.isEmpty(className)){
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Class names cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			return;
		}
		Course schoolClass= CourseDirectory.addNewClass(className, classDesc);
		System.out.println(CourseDirectory.getClasses().size());
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int n=schoolClassDao.add(con, schoolClass);
			if(n==1){
				//JOptionPane.showMessageDialog(null, "Class added successfully!");
				Object[] options = { "OK"};
				int result=JOptionPane.showOptionDialog(null, "Class added successfully!", "Successfully",  JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,  null, options, options[0]);

				this.txt_classname.setText("");
				this.txt_classdesp.setText("");
			}else{

				Object[] options = { "OK"};
				int result=JOptionPane.showOptionDialog(null, "Class added failed!", "Successfully",  JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE,  null, options, options[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Class added failed£¡");
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void clear(ActionEvent evt) {
		this.txt_classname.setText("");
		this.txt_classdesp.setText("");
	}

}
