package com.bridgelabz.employee_payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeePayroll {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String url = "root";
        String pswd = "Aa@9910147202";
        Connection con;

        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded !...");
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println("JDBC URl: "+jdbcURL);
            con = DriverManager.getConnection(jdbcURL, url, pswd);
            System.out.println("Connected Succssfully.. "+con);

            int id;
            String name;
            double salary;
            //Date start_date;
            Statement stmt;
            ResultSet rs;

            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery("select * from employee_payroll");

            while(rs.next()){
                id = rs.getInt(1);
                name = rs.getString(2);
                salary = rs.getDouble(4);
                //start_date = rs.getDate(4);
                employeePayrollList.add(new EmployeePayrollData(id, name, salary));

            }
            System.out.println(employeePayrollList);


            stmt.executeUpdate("update employee_payroll set name = 'billy' where name = 'Bill'");

//            while(rs.next()){
//                id = rs.getInt(1);
//                name = rs.getString(2);
//                salary = rs.getDouble(4);
//                //start_date = rs.getDate(4);
//                employeePayrollList.add(new EmployeePayrollData(id, name, salary));
//
//            }
//            System.out.println(employeePayrollList);
            rs.close();
            stmt.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
