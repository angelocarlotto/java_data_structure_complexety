import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;


public class DatabaseConnection {

    public static Connection getConnection() {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "123456");

            System.out.println("Database connected successfully!");

            return connection;

        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

}