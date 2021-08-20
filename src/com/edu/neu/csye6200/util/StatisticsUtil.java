package com.edu.neu.csye6200.util;

//import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.edu.neu.csye6200.model.Person;
import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.model.Teacher;

public class StatisticsUtil {

	private List<Student> stu; // teacher list
	private List<Teacher> tea; // student list
	private int stu_run_count = 0; // count for student thread run
	private int tea_run_count = 0; // count for teacher thread run
	private int stu_idx = 0; // index for locate stu obj
	private int tea_idx = 0; // index for locate Teacher obj
	private StringBuilder sb = new StringBuilder(); // build string for each student obj


	// default constructor to get two lists
	public StatisticsUtil() {
		stu = Student.getStuList();
		tea = Teacher.getTeaList();
	}


	public void studentDisplay(List<Person> stu) {

		int total_size = stu.size() - 1; // total size for Student collection
		boolean[] next = {false}; // flag for thread switch
		List<String> output = new ArrayList<String>(); // string list for return

		Thread identifyInfoThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (next) {
						while (stu_run_count <= total_size * 2) {
							while (next[0]) {
								next.wait();
							}

							sb.append("name: " + stu.get(stu_idx).getName() + ", sex: " + stu.get(stu_idx).getSex() + ", sn: " + stu.get(stu_idx).getSn() + ".");
							stu_run_count++;

							next[0] = true;
							next.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					// expected
					e.printStackTrace();
				}
			}
		});

		Thread relatedInfoThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					synchronized (next) {
						while (stu_run_count <= total_size * 2) {
							while (!next[0]) {
								next.wait();
							}

							sb.append(" address: " + stu.get(stu_idx).getAddress() + ", dept: " + stu.get(stu_idx).getDept() + ", class id: " + stu.get(stu_idx).getClassId() + ".");
							stu_run_count++;
							stu_idx++; // update student obj
							// System.out.println(sb.toString());
							output.add(sb.toString());
							sb = new StringBuilder(); // create a new builder for next obj

							next[0] = false;
							next.notifyAll();
						}
					}
				} catch (InterruptedException e) {
					// expected
					e.printStackTrace();
				}
			}
		});


		System.out.println("*** Start print info of Student Collection ***");

		identifyInfoThread.start();
		relatedInfoThread.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		identifyInfoThread.interrupt();
		relatedInfoThread.interrupt();

		System.out.println("*** Done ***");


	}

	public static void sortedByName(List<Person> personList) {
		System.out.println("***** Sort student by name *****");
		Collections.sort(personList, new Comparator<Person>() {
			@Override
			public int compare(Person s1, Person s2) {
				return s1.getName().compareTo(s2.getName());
			}
		});
	}

	/*
	 * function to sort student by their name
	 * @param - Collctions of Student objects
	 */
	public static void sortedBySN(List<Person> personList) {
		System.out.println("***** Sort student by name *****");
		Collections.sort(personList, new Comparator<Person>() {
			@Override
			public int compare(Person s1, Person s2) {
				return s1.getSn().compareTo(s2.getSn());
			}
		});
	}













}
	

	


/******** output **********
 * 
 * *** Strat print info of Student Collection ***
*** Done ***
name: Alex, sex: male, sn: B. address: 34659 Chi Street, dept: Bio, class id: 1.
name: Jason, sex: male, sn: K. address: 898 Newsom Ave, dept: Cs, class id: 3.
name: Crystal, sex: female, sn: L. address: 814 Luci Road, dept: Math, class id: 2.
Male: 2. Female: 1.
***** Sort student by dept *****
Alex,B,male,Bio,1,34659 Chi Street
Jason,K,male,Cs,3,898 Newsom Ave
Crystal,L,female,Math,2,814 Luci Road
***** Sort student by name *****
Alex,B,male,Bio,1,34659 Chi Street
Crystal,L,female,Math,2,814 Luci Road
Jason,K,male,Cs,3,898 Newsom Ave
***** Sort student by class id *****
Alex,B,male,Bio,1,34659 Chi Street
Crystal,L,female,Math,2,814 Luci Road
Jason,K,male,Cs,3,898 Newsom Ave
++++++++++++++++++++++++
*** Start print info of Teacher Collection ***
*** Done ***
name: Bob, sex: male, sn: B. address: 34659 Chi Street, dept: Bio, class id: 1.
name: Lili, sex: female, sn: K. address: 898 Newsom Ave, dept: Cs, class id: 3.
name: Iris, sex: female, sn: L. address: 814 Luci Road, dept: Math, class id: 2.
Teacher - Male: 1. Female: 2.
***** Sort teacher by name *****
Bob,B,male,Bio,1,34659 Chi Street
Lili,K,female,Cs,3,898 Newsom Ave
Iris,L,female,Math,2,814 Luci Road
***** Sort teacher by name *****
Bob,B,male,Bio,1,34659 Chi Street
Iris,L,female,Math,2,814 Luci Road
Lili,K,female,Cs,3,898 Newsom Ave
***** Sort teacher by class id *****
Bob,B,male,Bio,1,34659 Chi Street
Iris,L,female,Math,2,814 Luci Road
Lili,K,female,Cs,3,898 Newsom Ave
*****************************/
