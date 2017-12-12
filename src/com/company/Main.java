package com.company;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
	try{
	   //Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB\\testJava.db");
    }catch(SQLException e){
        System.out.println("Something went wrong " + e.getMessage());
    }
    }
}
