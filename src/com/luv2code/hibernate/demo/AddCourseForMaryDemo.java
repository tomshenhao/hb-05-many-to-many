package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCourseForMaryDemo {

	public static void main(String args[]) {
		
		SessionFactory factory=new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session=factory.getCurrentSession();
				
		try {
			
			session.beginTransaction();

			//get the student mary from database;
			int theId=2;
			
			Student tempStudent=session.get(Student.class, theId);
			
			System.out.println("\nLoaded student: "+tempStudent);
			System.out.println("Course: "+tempStudent.getCourses());
			
			//create more course
			Course tempCourse1=new Course("Rubik's Cube - How to Speed Cube");
			Course tempCourse2=new Course("Atari 2600 - Game Development");
			
			//add mary to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save the courses
			System.out.println("\nSaving the course ...");
			
			session.save(tempCourse1);
			session.save(tempCourse2);
						
			session.getTransaction().commit();
			System.out.println("Done!");
						
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.close();
			factory.close();
		}
		
	}
	
}
