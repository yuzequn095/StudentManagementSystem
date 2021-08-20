package com.edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.util.StringUtil;

public class StudentDao {

    /**
     * @param con
     * @param student
     * @return
     * @throws Exception
     */
    public int add(Connection con, Student student) throws Exception {
        String sql = "insert into t_student values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getSn());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, student.getDept());
        pstmt.setInt(5, student.getClassId());
        pstmt.setString(6, student.getAddress());
        return pstmt.executeUpdate();
    }

    /**
     * @param con
     * @param student
     * @return
     * @throws Exception
     */
    public ResultSet list(Connection con, Student student) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_student b left join t_school_class bt on b.classId=bt.id");
        ArrayList<String> conditions = new ArrayList<>();
        if (StringUtil.isNotEmpty(student.getName())) {
            conditions.add(" b.name like '%" + student.getName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSn())) {
            conditions.add(" b.sn like '%" + student.getSn() + "%'");
        }
        if (student.getClassId() != null && student.getClassId() != -1) {
            conditions.add(" b.classId=" + student.getClassId());
        }
        String query = sb.toString();
        if (!conditions.isEmpty()) {
            for (int i = 0; i < conditions.size(); i++) {
                if (i == 0) {
                    query = query + " where " + conditions.get(i);
                } else {
                    query = query + conditions.get(i);
                }
            }
        }
        PreparedStatement pstmt = con.prepareStatement(query);
        return pstmt.executeQuery();
    }

    public List<Person> studentList(Connection con) throws Exception {
        List<Person> allStudent = new ArrayList<>();
        ResultSet resultSet = list(con, new Student());
        while (resultSet.next()) {
            allStudent.add(new Student(resultSet.getString("name"),
                    resultSet.getString("sn"),
                    resultSet.getString("sex"),
                    resultSet.getString("dept"),
                    resultSet.getInt("classId"),
                    resultSet.getString("address")));

        }
        return allStudent;
    }

    /**
     * @param con
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Connection con, String id) throws Exception {

        String sql = "delete from t_student where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * @param con
     * @param student
     * @return
     * @throws Exception
     */
    public int update(Connection con, Student student) throws Exception {
        String sql = "update t_student set name=?,sn=?,sex=?,dept=?,address=?,classId=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getSn());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, student.getDept());
        pstmt.setString(5, student.getAddress());
        pstmt.setInt(6, student.getClassId());
        pstmt.setInt(7, student.getId());
        return pstmt.executeUpdate();
    }


    public boolean existStudentByclassId(Connection con, String classId) throws Exception {
        String sql = "select * from t_student where classId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, classId);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }
}
