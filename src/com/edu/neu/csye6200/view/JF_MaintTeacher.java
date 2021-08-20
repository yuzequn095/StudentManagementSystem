package com.edu.neu.csye6200.view;

import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.dao.TeacherDao;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Fac.TeacherFac;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class JF_MaintTeacher extends JInternalFrame {
	private JTable teacherTable;
	private JTextField t_nameTxt;
	private JTextField t_tnTxt;
	private JComboBox t_schoolClassJcb;
	private JRadioButton manJrb ;
	private JRadioButton femaleJrb ;
	private JTextArea addressTxt;
	private JComboBox schoolClassJcb ;

	private DbUtil dbUtil=new DbUtil();
	private CourseDao schoolClassDao=new CourseDao();
	private TeacherDao teacherDao=new TeacherDao();
	private JTextField idTxt;
	private JTextField nameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField deptTxt;
	private JTextField tnTxt;

	public JF_MaintTeacher() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		setClosable(true);
		setTitle("Maintain Teacher Info");
		setBounds(0, 0, GuiUtil.w-30, GuiUtil.h-90);
		JScrollPane scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(5,245,30,30);
		contentPane.add(lblNewLabel);

		idTxt = new JTextField();
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		idTxt.setBounds(35,245,50,30);
		contentPane.add(idTxt);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(95,245,60,30);
		contentPane.add(lblNewLabel_1);

		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(155,245,80,30);
		contentPane.add(nameTxt);

		JLabel label_3 = new JLabel("Sex");
		label_3.setBounds(245,245,30,30);
		contentPane.add(label_3);

		manJrb = new JRadioButton("male");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		manJrb.setBounds(295,245,100,30);
		contentPane.add(manJrb);

		femaleJrb = new JRadioButton("female");
		buttonGroup.add(femaleJrb);
		femaleJrb.setBounds(395,245,100,30);
		contentPane.add(femaleJrb);

		JLabel label_4 = new JLabel("Dept");
		label_4.setBounds(95,285,60,30);
		contentPane.add(label_4);

		deptTxt = new JTextField();
		deptTxt.setColumns(10);
		deptTxt.setEditable(false);
		deptTxt.setBounds(155,285,120,30);
		contentPane.add(deptTxt);

		JLabel lblNewLabel_2 = new JLabel("TID");
		lblNewLabel_2.setBounds(495,245,60,30);
		contentPane.add(lblNewLabel_2);

		tnTxt = new JTextField();
		tnTxt.setEditable(false);
		tnTxt.setColumns(10);

		tnTxt.setBounds(555,245,100,30);
		contentPane.add(tnTxt);



		JLabel label_5 = new JLabel("Class");
		label_5.setBounds(300,285,60,30);
		contentPane.add(label_5);

		schoolClassJcb = new JComboBox();
		schoolClassJcb.setBounds(400,285,255,30);
		contentPane.add(schoolClassJcb);

		JLabel label_6 = new JLabel("Addr");
		label_6.setBounds(95,340,60,30);
		contentPane.add(label_6);

		addressTxt = new JTextArea();
		addressTxt.setBounds(155,330,495,60);
		contentPane.add(addressTxt);

		JButton button_1 = new JButton("Update");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				updateTeacher(evt);
			}
		});
		button_1.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/modify.png")));
		button_1.setBounds(95,400,120,30);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				deleteStudent(evt);
			}
		});
		button_2.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/delete.png")));
		button_2.setBounds(400,400,120,30);
		contentPane.add(button_2);

		JLabel label = new JLabel("Name");
		label.setBounds(5,5,60,30);
		contentPane.add(label);

		t_nameTxt = new JTextField();
		t_nameTxt.setColumns(10);
		t_nameTxt.setBounds(65,5,70,30);
		contentPane.add(t_nameTxt);

		JLabel label_1 = new JLabel("TID");
		label_1.setBounds(140,5,70,30);
		contentPane.add(label_1);

		t_tnTxt = new JTextField();
		t_tnTxt.setColumns(10);
		t_tnTxt.setBounds(210,5,80,30);
		contentPane.add(t_tnTxt);

		JLabel label_2 = new JLabel("Class");
		label_2.setBounds(295,5,50,30);
		contentPane.add(label_2);

		t_schoolClassJcb = new JComboBox();
		t_schoolClassJcb.setBounds( 350,5,180,30);
		contentPane.add(t_schoolClassJcb);

		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		button.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/search.png")));
		button.setBounds(545,5,30,30);
		contentPane.add(button);

		JButton btn_refresh = new JButton();
		btn_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				t_nameTxt.setText("");
				t_tnTxt.setText("");

				if(t_schoolClassJcb.getItemCount()>0){
					t_schoolClassJcb.setSelectedIndex(0);
				}

				initTable(new Teacher());
			}
		});
		btn_refresh.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/refresh.png")));
		btn_refresh.setBounds(585,5,30,30);
		contentPane.add(btn_refresh);


		teacherTable = new JTable();
		teacherTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				teacherTableMousePressed(met);
			}
		});
		scrollPane.setViewportView(teacherTable);
		scrollPane.setBounds(5,40,GuiUtil.w-50,200);
		contentPane.add(scrollPane);

		teacherTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"ID", "Name", "TID", "Sex", "Department", "Home address", "Class"
				}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		teacherTable.getColumnModel().getColumn(5).setPreferredWidth(119);

		this.classesItem("search");
		this.classesItem("modify");
		this.initTable(new Teacher());
	}

	private void deleteStudent(ActionEvent evt) {
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)){
			//JOptionPane.showMessageDialog(null, "Select the record you want to delete");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Select the record you want to delete", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		}
		//int n=JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the record?");
		Object[] options = {"Yes", "No"};
		int n = JOptionPane.showOptionDialog(null, "Are you sure you want to delete the record?", "Warning", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if(n==0){
			Connection con=null;
			try{
				Teacher teacher = TeacherFac.getTeacherByTeacherNumber(tnTxt.getText());
				TeacherFac.removeTeacher(teacher);

				con=dbUtil.getCon();
				int deleteNum=teacherDao.delete(con, id);
				if(deleteNum==1){
					//JOptionPane.showMessageDialog(null, "Delete success");
					Object[] options1 = {"OK"};
					int result = JOptionPane.showOptionDialog(null, "Delete success!", "Warning", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options1[0]);
					this.clear();
					this.initTable(new Teacher());
				}else{
					JOptionPane.showMessageDialog(null, "Delete failed");
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Delete failed");
			}finally{
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void updateTeacher(ActionEvent evt) {
		String id = this.idTxt.getText();
		if(StringUtil.isEmpty(id)){
			//JOptionPane.showMessageDialog(null, "Select the record you want to modify");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Select the record you want to modify!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			return;
		}

		String tname=this.nameTxt.getText();
		String tn=this.tnTxt.getText();
		String dept=this.deptTxt.getText();
		String address=this.addressTxt.getText();

		if(StringUtil.isEmpty(tname)){
			//JOptionPane.showMessageDialog(null, "Student information name cannot be empty!");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Teacher name cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		}

		if(StringUtil.isEmpty(dept)){
			JOptionPane.showMessageDialog(null, "Teacher information cannot be empty!");
			return;
		}

		String sex="";
		if(manJrb.isSelected()){
			sex="male";
		}else if(femaleJrb.isSelected()){
			sex="female";
		}

		Course schoolClassTmp=(Course) schoolClassJcb.getSelectedItem();
		int scId=schoolClassTmp.getId();
//        Teacher teacher = TeacherFac.getTeacherByTeacherNumber(tn);
//        TeacherFac.removeTeacher(teacher);
		Teacher newOne = new Teacher(Integer.parseInt(id),  tname, tn, sex, dept,  scId,  address);
//        TeacherFac.getTeachers().add(newOne);
		System.out.println("There are " + TeacherFac.getTeachers().size() + " teachers");
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=teacherDao.update(con, newOne);
			if(addNum==1){
				//JOptionPane.showMessageDialog(null, "Teacher information modified successfully!");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Teacher information modified successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				clear();
				this.initTable(new Teacher());
			}else{
				//JOptionPane.showMessageDialog(null, "Student information modification failed!");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Teacher information modified successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Teacher information modification failed!");
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
		this.idTxt.setText("");
		this.nameTxt.setText("");
		this.tnTxt.setText("");
		this.deptTxt.setText("");
		this.manJrb.setSelected(true);
		this.addressTxt.setText("");
		if(this.schoolClassJcb.getItemCount()>0){
			this.schoolClassJcb.setSelectedIndex(0);
		}
	}


	private void teacherTableMousePressed(MouseEvent met) {
		int row=this.teacherTable.getSelectedRow();
		this.idTxt.setText((String)teacherTable.getValueAt(row, 0));
		this.nameTxt.setText((String)teacherTable.getValueAt(row, 1));
		this.tnTxt.setText((String)teacherTable.getValueAt(row, 2));
		String sex=(String)teacherTable.getValueAt(row, 3);
		if("male".equals(sex)){
			this.manJrb.setSelected(true);
		}else if("female".equals(sex)){
			this.femaleJrb.setSelected(true);
		}
		this.deptTxt.setText(teacherTable.getValueAt(row, 4)+"");
		this.addressTxt.setText((String)teacherTable.getValueAt(row, 5));
		String className=(String)this.teacherTable.getValueAt(row, 6);
		int n=this.schoolClassJcb.getItemCount();
		for(int i=0;i<n;i++){
			Course item=(Course)this.schoolClassJcb.getItemAt(i);
			if(item.getClassName().equals(className)){
				this.schoolClassJcb.setSelectedIndex(i);
			}
		}
	}


	private void searchTeacher(ActionEvent evt) {
		String bookName=this.t_nameTxt.getText();
		String author=this.t_tnTxt.getText();
		Course bookType=(Course) this.t_schoolClassJcb.getSelectedItem();
		int bookTypeId=bookType.getId();

		Teacher book=new Teacher(bookName,author,bookTypeId);
		this.initTable(book);
	}


	private void classesItem(String type){
		Connection con=null;
		Course schoolClass=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=schoolClassDao.list(con, new Course());
			if("search".equals(type)){
				schoolClass=new Course();
				schoolClass.setClassName("Please select...");
				schoolClass.setId(-1);
				schoolClass.setCalssDesc("");
				this.t_schoolClassJcb.addItem(schoolClass);
			}
			while(rs.next()){
				schoolClass=new Course();
				schoolClass.setClassName(rs.getString("className"));
				schoolClass.setId(rs.getInt("id"));
				schoolClass.setCalssDesc("");
				if("search".equals(type)){
					this.t_schoolClassJcb.addItem(schoolClass);
				}else if("modify".equals(type)){
					this.schoolClassJcb.addItem(schoolClass);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void initTable(Teacher book){
		DefaultTableModel dtm=(DefaultTableModel) teacherTable.getModel();
		dtm.setRowCount(0); // ???¡§??0??
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=teacherDao.list(con, book);
			while(rs.next()){
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("sn"));
				v.add(rs.getString("sex"));
				v.add(rs.getString("dept"));
				v.add(rs.getString("address"));
				v.add(rs.getString("className"));
				dtm.addRow(v);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}