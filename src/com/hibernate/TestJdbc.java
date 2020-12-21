package com.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {
    public static void main(String[] args) {
        String jdbcurl= "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
        String uname="hbstudent";
        String pass="hbstudent";

        try{
            System.out.println("Connecting to database: "+jdbcurl);
            Connection c= DriverManager.getConnection(jdbcurl,uname,pass);
            System.out.println("Connection Succesfull");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
