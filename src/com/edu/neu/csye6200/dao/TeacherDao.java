package com.edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.util.StringUtil;

public class TeacherDao {
    public int add(Connection con, Teacher teacher) throws Exception {
        String sql = "insert into t_teacher values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, teacher.getName());
        pstmt.setString(2, teacher.getSn());
        pstmt.setString(3, teacher.getSex());
        pstmt.setString(4, teacher.getDept());
        pstmt.setInt(5, teacher.getClassId());
        pstmt.setString(6, teacher.getAddress());
        return pstmt.executeUpdate();
    }


    public ResultSet list(Connection con, Teacher teacher) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_teacher b left join t_school_class bt on b.classId=bt.id");
        ArrayList<String> conditions = new ArrayList<>();
        if (StringUtil.isNotEmpty(teacher.getName())) {
            conditions.add(" b.name like '%" + teacher.getName() + "%'");
        }
        if (StringUtil.isNotEmpty(teacher.getSn())) {
            conditions.add(" b.sn like '%" + teacher.getSn() + "%'");
        }
        if (teacher.getClassId() != null && teacher.getClassId() != -1) {
            conditions.add(" b.classId=" + teacher.getClassId());
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

    public List<Person> teacherList(Connection con) throws Exception {
        List<Person> allTeacher = new ArrayList<>();
        ResultSet resultSet = list(con, new Teacher());
        while (resultSet.next()) {
            allTeacher.add(new Teacher(resultSet.getString("name"),
                    resultSet.getString("sn"),
                    resultSet.getString("sex"),
                    resultSet.getString("dept"),
                    resultSet.getInt("classId"),
                    resultSet.getString("address")));
        }
        return allTeacher;
    }

    /**
     * 学生信息删除
     *
     * @param con
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from t_teacher where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * 学生信息修改
     *
     * @param con
     * @param teacher
     * @return
     * @throws Exception
     */
    public int update(Connection con, Teacher teacher) throws Exception {
        String sql = "update t_teacher set name=?,sn=?,sex=?,dept=?,address=?,classId=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, teacher.getName());
        pstmt.setString(2, teacher.getSn());
        pstmt.setString(3, teacher.getSex());
        pstmt.setString(4, teacher.getDept());
        pstmt.setString(5, teacher.getAddress());
        pstmt.setInt(6, teacher.getClassId());
        pstmt.setInt(7, teacher.getId());
        return pstmt.executeUpdate();
    }

    /**
     * 指定班级下是否存在学生
     *
     * @param con
     * @param classId
     * @return
     * @throws Exception
     */
    public boolean existStudentByclassId(Connection con, String classId) throws Exception {
        String sql = "select * from t_teacher where classId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, classId);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }
}
