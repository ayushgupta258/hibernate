package com.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session=factory.getCurrentSession();
        try{
            session.beginTransaction();

            //print al students
            System.out.println("Printing all students");
            List<Student> allStudent = session.createQuery("from Student").getResultList();
            for(Student student: allStudent){
                System.out.println(student);
            }

            //printing with where clause
            System.out.println("\nPrinting with where clause");
            List<Student> whereStudents = session.createQuery("from Student s where s.firstName='MNO'").getResultList();
            for(Student student: whereStudents){
                System.out.println(student);
            }

            //printing with OR
            System.out.println("\nPrinting with OR");
            List<Student> orStudents = session.createQuery("from Student s where s.firstName='MNO' or s.lastName='ABC'").getResultList();
            for(Student student: orStudents){
                System.out.println(student);
            }

            //printing with LIKE
            System.out.println("\nPrinting with LIKE");
            List<Student> likeStudents = session.createQuery("from Student s where s.firstName like '%O'").getResultList();
            for(Student student: likeStudents){
                System.out.println(student);
            }

            System.out.println("Done......");

//            Updating name of 1 Student
            Student myStudent = session.get(Student.class,8);
            myStudent.setFirstName("Ayush");
            System.out.println("Student name updated successfully");

//            Updating multiple records at some time
            session.createQuery("update Student set firstName='Ayush' where id=4").executeUpdate();
            System.out.println("Multiple Students name updated successfully");

//            Deleting Student using way 1
            System.out.println("Deleting..............");
            session.delete(myStudent);
            System.out.println("Delete using way 1 successful................");

//            Deleting using way 2
            System.out.println("Deleting..............");
            session.createQuery("delete from Student where id=3").executeUpdate();
            System.out.println("Delete using way 2 successful................");

            session.getTransaction().commit();
        }finally {
            factory.close();
        }
    }
}
