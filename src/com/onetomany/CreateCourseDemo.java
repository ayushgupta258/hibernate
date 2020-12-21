package com.onetomany;

import com.onetomany.entity.Course;
import com.onetomany.entity.Instructor;
import com.onetomany.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        Session session=factory.getCurrentSession();

        try{
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, 1);
//
//            Course english = new Course("English");
//            Course computer = new Course("Computer");
//
//            instructor.add(english);
//            instructor.add(computer);
//
//            session.save(english);
//            session.save(computer);

            //Retrieving the Data git commit
            System.out.println(session.get(Instructor.class, 1).getCourses());

            System.out.println("Done.............");

            session.getTransaction().commit();
        }finally {
            factory.close();
        }
    }
}
