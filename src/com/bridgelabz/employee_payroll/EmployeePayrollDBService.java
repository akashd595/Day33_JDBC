package com.bridgelabz.employee_payroll;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    public List<EmployeePayrollData> readData(){
        String sql = "SELECT * from employee_payroll; ";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try(Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate startDate = resultSet.getDate("start").toLocalDate();

                employeePayrollDataList.add(new EmployeePayrollData(id, name, salary, startDate));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return employeePayrollDataList;
    }

    private Connection getConnection() throws SQLException {
        Connection con;
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String pswd = "Aa@9910147202";
        System.out.println("Connection to database: "+jdbcURL);
        con = DriverManager.getConnection(jdbcURL, userName, pswd);
        System.out.println("Connection successfully !!!"+con);

        return con;
    }
}
