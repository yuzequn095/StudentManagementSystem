package com.edu.neu.csye6200.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.edu.neu.csye6200.dao.StudentDao;
import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.model.Fac.StudentFac;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

public class JF_MaintStudent extends JInternalFrame {
	private JTable studentTable;
	private JTextField s_nameTxt;
	private JTextField nuidTxt;
	private JComboBox JCB_Class;
	private JRadioButton manJrb ;
	private JRadioButton femaleJrb ;
	private JTextArea addressTxt;
	private JComboBox schoolClassJcb ;

	private DbUtil dbUtil=new DbUtil();
	private CourseDao schoolClassDao=new CourseDao();
	private StudentDao studentDao=new StudentDao();
	private JTextField idTxt;
	private JTextField nameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField deptTxt;
	private JTextField nuidtxt;

	public JF_MaintStudent() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(null);
		setClosable(true);
		setTitle("Maintain Student Info");
		setBounds(0, 0, GuiUtil.w-30, GuiUtil.h-90);

		JScrollPane scrollPane = new JScrollPane();


		JLabel label = new JLabel("Name");
		//label.setBounds(0,5,80,30);
		label.setBounds(5,5,60,30);
		contentPane.add(label);

		s_nameTxt = new JTextField();
		s_nameTxt.setColumns(10);
		s_nameTxt.setBounds(65,5,70,30);
		contentPane.add(s_nameTxt);

		JLabel label_1 = new JLabel("NUID");
		label_1.setBounds(140,5,70,30);
		contentPane.add(label_1);

		nuidTxt = new JTextField();
		nuidTxt.setColumns(10);
		nuidTxt.setBounds(210,5,80,30);
		contentPane.add(nuidTxt);

		JLabel label_2 = new JLabel("Class");
		label_2.setBounds(295,5,50,30);
		contentPane.add(label_2);

		JCB_Class = new JComboBox();
		JCB_Class.setBounds( 350,5,180,30);
		contentPane.add(JCB_Class);

		JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchStudent(e);
			}
		});
		button.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/search.png")));
		button.setBounds(545,5,30,30);
		contentPane.add(button);

		JButton btn_refresh = new JButton();
		btn_refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clear();
				s_nameTxt.setText("");
				nuidTxt.setText("");

				if(JCB_Class.getItemCount()>0){
					JCB_Class.setSelectedIndex(0);
				}

				initTable(new Student());
			}
		});
		btn_refresh.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/refresh.png")));
		btn_refresh.setBounds(585,5,30,30);
		contentPane.add(btn_refresh);


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
		nameTxt.setEditable(false);
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

		JLabel lblNewLabel_2 = new JLabel("NUID");
		lblNewLabel_2.setBounds(495,245,60,30);
		contentPane.add(lblNewLabel_2);

		nuidtxt = new JTextField();
		nuidtxt.setEditable(false);
		nuidtxt.setColumns(10);
		nuidtxt.setBounds(555,245,100,30);
		contentPane.add(nuidtxt);

		JLabel label_4 = new JLabel("Dept");
		label_4.setBounds(95,285,60,30);
		contentPane.add(label_4);

		deptTxt = new JTextField();
		deptTxt.setColumns(10);
		deptTxt.setEditable(false);
		deptTxt.setBounds(155,285,120,30);
		contentPane.add(deptTxt);




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
			@Override
			public void actionPerformed(ActionEvent evt) {
				updateStudent(evt);
			}
		});
		button_1.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/modify.png")));

		button_1.setBounds(95,400,120,30);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Delete");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				deleteStudent(evt);
			}
		});
		button_2.setIcon(new ImageIcon(JF_MaintStudent.class.getResource("/com/edu/neu/csye6200/images/delete.png")));

		button_2.setBounds(400,400,120,30);
		contentPane.add(button_2);



		studentTable = new JTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent met) {
				studentTableMousePressed(met);
			}
		});
		scrollPane.setViewportView(studentTable);
		scrollPane.setBounds(5,40,GuiUtil.w-50,200);
		contentPane.add(scrollPane);

		studentTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"ID", "Name", "NUID", "Sex", "Department", "Address", "Class"}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			@Override
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		studentTable.getColumnModel().getColumn(5).setPreferredWidth(119);

		this.classesItem("search");
		this.classesItem("modify");
		this.initTable(new Student());
	}


	private void deleteStudent(ActionEvent evt) {
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)){
			//JOptionPane.showMessageDialog(null, "Select the record you want to delete");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Select the record you want to delete!", "Warning", JOptionPane.DEFAULT_OPTION,
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
				/**
				 * delete student find the obj by sn first, then delete it from the list
				 */
				Student student = StudentFac.getStudentBySn(nuidtxt.getText());
				StudentFac.removeStudent(student);
				System.out.println(StudentFac.getStudents().size());
				/**
				 * delete from database
				 */
				con=dbUtil.getCon();
				int deleteNum=studentDao.delete(con, id);
				if(deleteNum==1){
					//JOptionPane.showMessageDialog(null, "Delete success");
					Object[] options1 = {"OK"};
					int result = JOptionPane.showOptionDialog(null, "Delete success!", "Warning", JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, options1[0]);
					this.clear();
					this.initTable(new Student());

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


	private void updateStudent(ActionEvent evt) {
		String id=this.idTxt.getText();
		if(StringUtil.isEmpty(id)){
			//JOptionPane.showMessageDialog(null, "Select the record you want to modify");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Select the record you want to modify!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		}
		String name = this.nameTxt.getText();
		String sn = this.nuidtxt.getText();
		String dept=this.deptTxt.getText();
		String address=this.addressTxt.getText();

		if(StringUtil.isEmpty(dept)){
			//JOptionPane.showMessageDialog(null, "Student information department cannot be empty!");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Student information department cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
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
//		Student oldOne = StudentFac.getStudentBySn(sn);
//		StudentFac.removeStudent(oldOne);
		Student student =new Student(Integer.parseInt(id),  name, sn, sex, dept,  scId,  address);
//		StudentFac.getStudents().add(student);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int addNum=studentDao.update(con, student);
			if(addNum==1){
				//JOptionPane.showMessageDialog(null, "Student information modified successfully!");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Student information modified successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				clear();
				this.initTable(new Student());
			}else{
				//JOptionPane.showMessageDialog(null, "Student information modification failed!");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Student information modification failed!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Student information modification failed!");
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
		this.nuidtxt.setText("");
		this.deptTxt.setText("");
		this.manJrb.setSelected(true);
		this.addressTxt.setText("");
		if(this.schoolClassJcb.getItemCount()>0){
			this.schoolClassJcb.setSelectedIndex(0);
		}
	}


	private void studentTableMousePressed(MouseEvent met) {
		int row=this.studentTable.getSelectedRow();
		this.idTxt.setText((String)studentTable.getValueAt(row, 0));
		this.nameTxt.setText((String)studentTable.getValueAt(row, 1));
		this.nuidtxt.setText((String)studentTable.getValueAt(row, 2));
		String sex=(String)studentTable.getValueAt(row, 3);
		if("male".equals(sex)){
			this.manJrb.setSelected(true);
		}else if("female".equals(sex)){
			this.femaleJrb.setSelected(true);
		}
		this.deptTxt.setText(studentTable.getValueAt(row, 4)+"");
		this.addressTxt.setText((String)studentTable.getValueAt(row, 5));
		String className=(String)this.studentTable.getValueAt(row, 6);
		int n=this.schoolClassJcb.getItemCount();
		for(int i=0;i<n;i++){
			Course item=(Course)this.schoolClassJcb.getItemAt(i);
			if(item.getClassName().equals(className)){
				this.schoolClassJcb.setSelectedIndex(i);
			}
		}
	}


	private void searchStudent(ActionEvent evt) {
		String stuname=this.s_nameTxt.getText();
		String nuid=this.nuidTxt.getText();
		Course selectcourse=(Course) this.JCB_Class.getSelectedItem();
		int selectcourseId=selectcourse.getId();

		Student newStudent=new Student(stuname,nuid,selectcourseId);
		this.initTable(newStudent);
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
				this.JCB_Class.addItem(schoolClass);
			}
			while(rs.next()){
				schoolClass=new Course();
				schoolClass.setClassName(rs.getString("className"));
				schoolClass.setId(rs.getInt("id"));
				schoolClass.setCalssDesc("");
				if("search".equals(type)){
					this.JCB_Class.addItem(schoolClass);
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


	private void initTable(Student student){
		DefaultTableModel dtm=(DefaultTableModel) studentTable.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try{
			con=dbUtil.getCon();
			ResultSet rs=studentDao.list(con, student);
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