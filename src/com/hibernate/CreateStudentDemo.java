package com.hibernate;

import com.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            //use session object to save the student object

            System.out.println("Creating student object............");
            Student student1=new Student("EFG","XYZ","efg@gmail.com");
            Student student2=new Student("PQR","ABC","pqr@gmail.com");
            Student student3=new Student("MNO","ABC","mno@gmail.com");

            session.beginTransaction();

            System.out.println("Saving the data..........");
            session.save(student1);
            session.save(student2);
            session.save(student3);

            System.out.println(student3);

//            Getting the student from the databse
            System.out.println("Getting the student object from the database of Student Id"+ student1.getId());
            Student myStudent=session.get(Student.class,2);
            System.out.println("Get Completed "+myStudent);

            session.getTransaction().commit();

            System.out.println("Done...........");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            factory.close();
        }
    }
}
