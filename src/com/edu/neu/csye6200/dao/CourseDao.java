package com.edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.util.StringUtil;


public class CourseDao {

	public int add(Connection con, Course schoolClass)throws Exception{
		String sql="insert into t_school_class values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, schoolClass.getClassName());
		System.out.println(schoolClass.getClassName());
		pstmt.setString(2, schoolClass.getCalssDesc());
		return pstmt.executeUpdate();
	}

	public ResultSet list(Connection con,Course schoolClass)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_school_class");
		if(StringUtil.isNotEmpty(schoolClass.getClassName())){
			sb.append(" and className like '%"+schoolClass.getClassName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}


	

	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_school_class where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	

	public int update(Connection con,Course schoolClass)throws Exception{
		String sql="update t_school_class set className=?,classDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, schoolClass.getClassName());
		pstmt.setString(2, schoolClass.getCalssDesc());
		pstmt.setInt(3, schoolClass.getId());
		return pstmt.executeUpdate();
	}

	public boolean existClassById(Connection con, String classId) throws Exception {
		String sql = "select * from t_school_class where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}
}
