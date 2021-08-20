package com.edu.neu.csye6200.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import javax.swing.*;
import com.edu.neu.csye6200.dao.UserDao;
import com.edu.neu.csye6200.model.User;
import com.edu.neu.csye6200.model.Fac.CourseDirectory;
import com.edu.neu.csye6200.model.Fac.StudentFac;
import com.edu.neu.csye6200.model.Fac.TeacherFac;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;
import com.edu.neu.csye6200.util.StringUtil;

public class JF_Driver extends JFrame {

	CourseDirectory cd = new CourseDirectory();
	StudentFac sf = new StudentFac();
	TeacherFac tf = new TeacherFac();


	private JTextField txt_username;
	private JPasswordField txt_password;
	private JPanel contentPane;


	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					initFont(GuiUtil.font);
					JF_Driver frame = new JF_Driver();
					frame.setResizable(false);
					frame.setTitle("Information Management System");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(GuiUtil.x, GuiUtil.y, GuiUtil.w, GuiUtil.h);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//set global font
	public static void initFont(Font font){

		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}

	}


	public JF_Driver() {

		contentPane = new JPanel();
		contentPane.setLayout(null);

		JLabel backgroundLabel=new JLabel("Welcome NEU Management System");
		backgroundLabel.setFont(new Font("DIN",Font.BOLD,28));
		URL url= JF_Driver.class.getResource("neu.png");


		backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundLabel.setBounds(100,55,500,80);


		JLabel lb_username = new JLabel("Username");
		lb_username.setBounds(180,215,100,20);

		JLabel lb_psw = new JLabel("Password");
		lb_psw.setBounds(180,275,100,20);

		txt_username = new JTextField();
		txt_username.setBounds(280,210,200,30);
		txt_password = new JPasswordField();
		txt_password.setBounds(280,270,200,30);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(180,380,100,30);
		btnLogin.setIcon(new ImageIcon(JF_AddNewClass.class.getResource("/com/edu/neu/csye6200/images/add.png")));

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login(e);
			}
		});

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(380,380,100,30);
		btnClear.setIcon(new ImageIcon(JF_AddNewClass.class.getResource("/com/edu/neu/csye6200/images/clear.png")));
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearTxt(e);
			}
		});


		contentPane.add(lb_username);
		contentPane.add(lb_psw);
		contentPane.add(txt_username);
		contentPane.add(txt_password);
		contentPane.add(btnLogin);
		contentPane.add(btnClear);
		contentPane.add(backgroundLabel);

		this.setContentPane(contentPane);
		this.setLocationRelativeTo(null);
	}


	private void Login(ActionEvent evt) {
		String userName=this.txt_username.getText();
		String password=new String(this.txt_password.getPassword());
		if(StringUtil.isEmpty(userName)){
			//JOptionPane.showMessageDialog(null, "Username cannot be empty!");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Username cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		}
		if(StringUtil.isEmpty(password)){
			//JOptionPane.showMessageDialog(null, "Password cannot be empty!");
			Object[] options = {"OK"};
			int result = JOptionPane.showOptionDialog(null, "Password cannot be empty!", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			return;
		}
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser!=null){
				dispose();
				new JF_Main().setVisible(true);
			}else{
				//JOptionPane.showMessageDialog(null, "Incorrect user name or password!");
				Object[] options = {"OK"};
				int result = JOptionPane.showOptionDialog(null, "Incorrect user name or password!", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void clearTxt(ActionEvent evt) {
		this.txt_username.setText("");
		this.txt_password.setText("");
	}
}
