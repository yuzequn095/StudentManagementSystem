package com.edu.neu.csye6200.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.edu.neu.csye6200.dao.StudentDao;
import com.edu.neu.csye6200.dao.TeacherDao;
import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.model.Teacher;

import javax.swing.*;

public class CsvAPI {
	public static void write(String s, String[] CsvFile) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(s);
			BufferedWriter out = new BufferedWriter(fw);

			System.out.println("\n" + "BufferedWriter: " + s
					+ " write " + CsvFile.length + " items" + "\n");

			for (String name: CsvFile) {
				out.write(name);
				out.newLine();
			}
			out.flush();
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (Exception e2) {
					e2.printStackTrace();;
				}
			}
		}
	}

	public static List<Person> read(String s) {
		List<Person> list = new ArrayList<>();
		FileReader fr = null;
		try
		{
			fr = new FileReader(s);
			@SuppressWarnings("resource")
			BufferedReader inLine = new BufferedReader(fr);
			String inputLine = null;
			while ((inputLine = inLine.readLine()) != null) {
				String[] strs = inputLine.split(",");
				// whatever student or teacher
				String name = strs[0];
				String sn = strs[1];
				String sex = strs[2];
				String dept = strs[3];
				String classId = strs[4];
				String address = strs[5];
				list.add(new Person(name, sn, sex, dept, Integer.parseInt(classId), address));

			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	public static void  runCSV(){
		String FILE_NAME = "students.csv";
		DbUtil dbUtil=new DbUtil();
		StudentDao studentDao=new StudentDao();
		TeacherDao teacherDao=new TeacherDao();
		Connection con=null;

		List<String> v=new ArrayList();
		v.add("********Students Information********");
		try{
			con=dbUtil.getCon();
			ResultSet rs=studentDao.list(con, new Student());
			while(rs.next()){

				v.add(
						rs.getString("id")+","+
								rs.getString("name")+","+
								rs.getString("sn")+","+
								rs.getString("sex")+","+
								rs.getString("dept")+","+
								rs.getString("address")+","
								+rs.getString("className"));

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
		v.add("");
		v.add("********Teachers Information********");
		try{
			con=dbUtil.getCon();
			ResultSet rs=teacherDao.list(con, new Teacher());
			while(rs.next()){
				v.add(
						rs.getString("id")+","+
								rs.getString("name")+","+
								rs.getString("sn")+","+
								rs.getString("sex")+","+
								rs.getString("dept")+","+
								rs.getString("address")+","
								+rs.getString("className"));

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
		String[] CsvFile= v.toArray(new String[v.size()]);
		write(FILE_NAME, CsvFile);

		Object[] options = {"OK"};
		int result = JOptionPane.showOptionDialog(null, "Export CSV successfully!", "Warning", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);




	}
	public static void main(String[] args) {
		runCSV();

	}
}


