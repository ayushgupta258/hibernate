package com.onetomany;

import com.onetomany.entity.Course;
import com.onetomany.entity.Instructor;
import com.onetomany.entity.InstructorDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class CreateDemo {
    public static void main(String[] args) {
        SessionFactory factory=new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetails.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();
        Session session=factory.getCurrentSession();

        try{
            Instructor instructor=new Instructor("Ayush","Gupta","ayush@gmial.com");
            InstructorDetails instructorDetails = new InstructorDetails("youtube.com","Music");

            //associate the objects
            instructorDetails.setInstructor(instructor);

            session.beginTransaction();

            //this will also delete the associated object due to cascade
//            session.delete(session.get(Instructor.class,2));

            //this will also save the associated object due to cascade
            System.out.println("Saving Data\n"+instructorDetails);
            session.save(instructorDetails);
            session.getTransaction().commit();

            System.out.println("Done.............");
        }finally {
            session.close();
            factory.close();
        }
    }
}
