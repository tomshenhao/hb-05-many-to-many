package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

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

			int theId = 2;
			
			Student tempStudent=session.get(Student.class, theId);
			
			System.out.println("Student information: "+tempStudent);
			System.out.println("Course:"+tempStudent.getCourses());
			
			session.delete(tempStudent);
			
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
