package com.company;

import java.net.URL;
import java.sql.*;

public class Main {
    public static final String DB_NAME = "testJava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB\\"+DB_NAME;

    public static final String TABLE_CONTACTS ="contacts" ;

    public static final String COLUMN_NAME="name";
    public static final String COLUMN_PHONE="phone";
    public static final String COLUMN_EMAIL="email";

    public static void main(String[] args) {
	try{
	   //Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Bartek\\IdeaProjects\\TestDB\\testJava.db");
        Statement statement= conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_CONTACTS +"("+
                COLUMN_NAME+"TEXT," + COLUMN_PHONE+"INTEGER,"+ COLUMN_EMAIL+"TEXT"+" )";
        statement.execute(sql);
        statement.execute("INSERT INTO "+ TABLE_CONTACTS+ "("+ COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")" +
                " VALUES ( 'Timmy', 503535999, 'timmy@wp.pl')");
        statement.execute("INSERT INTO "+ TABLE_CONTACTS+ "("+ COLUMN_NAME+","+COLUMN_PHONE+","+COLUMN_EMAIL+ ")" +
                " VALUES ( 'Jimmy', 656111222, 'jimmy@wp.pl')");
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
