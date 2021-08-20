package com.edu.neu.csye6200.model.Fac;

import java.util.ArrayList;
import java.util.List;

import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Student;

/**
 * @author Runyao Xia
 * @date: 2021/8/18
 */
public class StudentFac {
    static List<Person> students;

    {
        students = new ArrayList<>();
    }

    public static Student addNewStudent(String name, String sn, String sex, String dept, Integer calssId, String address) {
        Person s = new Student(name, sn, sex, dept, calssId, address);
        students.add((Student) s);
        return (Student) s;
    }

    public static Student getStudentBySn(String studentNumber) {
        for (Person s : students) {
            System.out.println(s.getSn());
            if (s.getSn().equals(studentNumber)) {
                return (Student) s;
            }
        }
        return null;
    }

    public static void removeStudent(Student target) {
        for (Person s : students) {
            if (s.getName().equals(target.getName())) {
                students.remove(students.indexOf(s));
                break;
            }
        }
    }

    public static void updateStudentDept(Person target, String newDept) {
        for (Person s : students) {
            if (s.getName().equals(target.getName())) {
                s.setDept(newDept);
            }
        }
    }

    public static void updateStudentCalssId(Person target, Integer newCalssId) {
        for (Person s : students) {
            if (s.getName().equals(target.getName())) {
                s.setClassId(newCalssId);
            }
        }
    }

    public static void updateStudentAddress(Person target, String newAddress) {
        for (Person s : students) {
            if (s.getName().equals(target.getName())) {
                s.setAddress(newAddress);
            }
        }
    }

    public static List<Person> getStudents() {
        return students;
    }
}
