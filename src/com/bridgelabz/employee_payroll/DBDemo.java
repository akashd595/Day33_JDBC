package com.bridgelabz.employee_payroll;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String url = "root";
        String pswd = "Aa@9910147202";
        Connection con;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded !...");
        } catch(Exception e){
            e.printStackTrace();
        }

        listDrivers();

        try {
            System.out.println("JDBC URl: "+jdbcURL);
            con = DriverManager.getConnection(jdbcURL, url, pswd);
            System.out.println("Connected Succssfully.. "+con);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while(driverList.hasMoreElements()){
            Driver driverClass = (Driver)driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}
