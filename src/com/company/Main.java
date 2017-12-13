package com.company;

import java.net.URL;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
	try{
	   //Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB\\testJava.db");
        Statement statement= conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS contacts" +
                " (name TEXT, phone INTEGER, email TEXT)";
        statement.execute(sql);
//        statement.execute("INSERT INTO contacts (name, phone, email)" +
//                " VALUES ( 'Tim', 12212, 'tim@wp.pl')");
//        statement.execute("INSERT INTO contacts (name, phone, email)" +
//                " VALUES ('Joe', 50212, 'joe@wp.pl')");
//        statement.execute("UPDATE contacts SET phone=99999 WHERE name='Joe'");
//        statement.execute("DELETE FROM contacts WHERE name='Joe' ");
        ResultSet results = statement.executeQuery("SELECT * FROM contacts");
        while(results.next()){
            System.out.println(results.getString("name")+ " " +
                                results.getInt("phone")+   " " +
                                results.getString("email") + " "
            ) ;


        }
        statement.close();
        conn.close();
    }catch(SQLException e){
        System.out.println("Something went wrong " + e.getMessage());
    }
    }
}
