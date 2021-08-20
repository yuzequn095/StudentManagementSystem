package com.edu.neu.csye6200.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    // for student list
    private static List<Student> stu_list = new ArrayList<>();

    public Student() {
        super();
    }


    public Student(String name, String sn, String sex, String dept, Integer classId, String address) {
        super(name, sn, sex, dept, classId, address);
        // add student obj to list
        stu_list.add(this);
    }


    public Student(int id, String name, String sn, String sex, String dept, Integer classId, String address) {
        super(id, name, sn, sex, dept, classId, address);


    }


    public Student(String name, String sn, Integer calssId) {
        super(name, sn, calssId);

    }

    /*
     * Funtion to return list
     */
    public static List<Student> getStuList() {
        return stu_list;
    }

//	public String toString() {
//		return  this.getName() + "," + this.getSn() + "," + this.getSex() + "," + this.getDept() + "," + this.getClassId() + "," + this.getAddress();
//	}


}
