package com.edu.neu.csye6200.model.Fac;

import java.util.*;

import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Teacher;

public class TeacherFac {
	static List<Person> teachers;
	{
		teachers = new ArrayList<>();
	}
	public static Teacher addNewTeacher(String name, String sn, String sex, String dept, Integer calssId, String address) {
		Person t = new Teacher(name, sn, sex, dept, calssId, address);
		teachers.add(t);
		return (Teacher)t;
	}
	public static Teacher getTeacherByTeacherNumber(String teacherId) {
    	for (Person t:teachers) {
    		if(t.getSn().equals(teacherId)) {
    			return (Teacher)t;
    		}
    	}
		return null;
    }
	
	public static void removeTeacher(Person target) {
//		for (Person t: teachers) {
//			if (t.getName().equals(target.getName())) {
//				teachers.remove(teachers.indexOf(t));
//			}
//		}
		Iterator<Person> it_b=teachers.iterator();
		while(it_b.hasNext()){
			Person t=it_b.next();
			if (t.getName().equals(target.getName())){
				it_b.remove();
			}

		}
	}
	
	public static void updateTeacherDept(Person target, String newDept) {
		for (Person t: teachers) {
			if (t.getName().equals(target.getName())) {
				t.setDept(newDept);
			}
		}
	}
	
	public static void updateTeacherCalssId(Person target, Integer newCalssId) {
		for (Person t: teachers) {
			if (t.getName().equals(target.getName())) {
				t.setClassId(newCalssId);
			}
		}
	}
	
	public static void updateTeacherAddress(Person target, String newAddress) {
		for (Person t: teachers) {
			if (t.getName().equals(target.getName())) {
				t.setAddress(newAddress);
			}
		}
	}
	public static List<Person> getTeachers() {
    	return teachers;
    }
}
