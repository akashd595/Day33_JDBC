package com.bridgelabz.employee_payroll;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {
    private PreparedStatement employeePayrollDataStatement;
    private static EmployeePayrollDBService employeePayrollDBService;

    private EmployeePayrollDBService(){

    }

    public static EmployeePayrollDBService getInstance(){
        if(employeePayrollDBService == null)
            employeePayrollDBService = new EmployeePayrollDBService();

        return employeePayrollDBService;
    }
    public List<EmployeePayrollData> readData(){
        String sql = "SELECT * from employee_payroll; ";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();

        try(Connection connection = this.getConnection()){

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            employeePayrollDataList = this.getEmployeePayrollData(resultSet);
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                double salary = resultSet.getDouble("salary");
//                LocalDate startDate = resultSet.getDate("start").toLocalDate();
//
//                employeePayrollDataList.add(new EmployeePayrollData(id, name, salary, startDate));
//            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return employeePayrollDataList;
    }
    public List<EmployeePayrollData> getEmployeePayrollData(String name) throws SQLException {

        List<EmployeePayrollData> employeePayrollDataList = null;
        if(this.employeePayrollDataStatement == null)
            this.preparedStatementForEmployeeData();
        try{
            employeePayrollDataStatement.setString(1, name);
            ResultSet resultSet = employeePayrollDataStatement.executeQuery();
            employeePayrollDataList = this.getEmployeePayrollData(resultSet);

        } catch (Exception e){
            e.printStackTrace();
        }

        return employeePayrollDataList;
    }
    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet){
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try{
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

    private void preparedStatementForEmployeeData() throws SQLException {
        try{
            Connection con = this.getConnection();
            String sql = "SELECT * FROM employee_payroll WHERE name = ?";
            employeePayrollDataStatement = con.prepareStatement(sql);
        } catch (Exception e){
            e.printStackTrace();
        }

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

    public int updateEmployeeSalary(String name, double salary) {

        return this.updateEmployeeDataUsingStatement(name, salary);
    }

    private int updateEmployeeDataUsingStatement(String name, double salary) {
        String sql = String.format("update employee_payroll set salary = %.2f where name = '%s' ;",salary, name);

        try (Connection con = this.getConnection()){

            Statement statement = con.createStatement();
            return statement.executeUpdate(sql);            //executeQuery return number of rows updated

        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }


}
