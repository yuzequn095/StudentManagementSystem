package com.edu.neu.csye6200.model.Fac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.edu.neu.csye6200.model.Course;

/**
 * @author Runyao Xia
 * @date: 2021/8/18
 */
public class CourseDirectory {
    static List<Course> classes;
    {
        classes = new ArrayList<>();
    }

    public CourseDirectory() {
    }
    public static Course addNewClass(String className, String classDesc) {
        Course sc = new Course(className, classDesc);
        classes.add(sc);
        return sc;
    }
    public static Course getClassByName(String name) {
    	for (Course sc : classes) {
    		if (sc.getClassName().equals(name)) {
    			return sc;
    		}
    	}
    	return null;
    }
    public static void removeClass(Course target) {
        Iterator<Course> it_b=classes.iterator();

//		for (Course sc: classes) {
//			if (sc.getClassName().equals(target.getClassName())) {
//				classes.remove(classes.indexOf(sc));
//			}
//		}
        while(it_b.hasNext()){
            Course cs=it_b.next();
            if ((target.getClassName()).equals(cs.getClassName())){
                it_b.remove();
            }

        }
	}
    public static List<Course> getClasses() {
        return classes;
    }


}
