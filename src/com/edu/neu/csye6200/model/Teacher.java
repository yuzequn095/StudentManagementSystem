package com.edu.neu.csye6200.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {

    private static List<Teacher> tea_list = new ArrayList<>();

    public Teacher() {
        super();
    }


    public Teacher(String name, String tn, String sex, String dept, Integer calssId, String address) {
        super(name, tn, sex, dept, calssId, address);
        tea_list.add(this); // add current teacher obj to static list

    }


    public Teacher(int id, String name, String sn, String sex, String dept, Integer calssId, String address) {
        super(id, name, sn, sex, dept, calssId, address);

    }


    public Teacher(String name, String sn, Integer calssId) {
        super(name, sn, calssId);

    }

    /*
     * Funtion to return static list
     */
    public static List<Teacher> getTeaList() {
        return tea_list;
    }

}
