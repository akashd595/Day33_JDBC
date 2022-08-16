package com.bridgelabz.employee_payroll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService {




    public enum IOService {
        CONSOLE_IO, FILE_IO, DB_IO, REST_IO
    }

    public List<EmployeePayrollData> employeePayrollList;
    private EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollService() {
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this();
        this.employeePayrollList = employeePayrollList;
    }

    public static void main(String[] args) {

        List<EmployeePayrollData> employeePayrollList = new ArrayList<EmployeePayrollData>();

        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);

        Scanner consoleInputReader = new Scanner(System.in);

        employeePayrollService.readEmployeeData(consoleInputReader);

//        employeePayrollService.writeEmployeeData(IOService.CONSOLE_IO);
    }

    public void readEmployeeData(Scanner consoleInputReader) {
        System.out.println("Enter employee ID : ");
        int id = Integer.parseInt(consoleInputReader.nextLine());
        System.out.println("Enter employee name : ");
        String name = consoleInputReader.nextLine();
        System.out.println("Enter employee salary : ");
        double salary = Double.parseDouble(consoleInputReader.nextLine());

        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }
    public List<EmployeePayrollData> readEmployeeData(IOService ioService) {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollList = employeePayrollDBService.readData();

        return employeePayrollList;
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayrollDBService.updateEmployeeSalary(name, salary);
        if(result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeeData(name);
        if(employeePayrollData != null) employeePayrollData.salary = salary;
    }

    private EmployeePayrollData getEmployeeData(String name) {


        EmployeePayrollData employeePayrollData;
        employeePayrollData = this.employeePayrollList.stream()
                .filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name))
                .findFirst()
                .orElse(null);

        return employeePayrollData;
    }
    public boolean checkEmployeeSyncWithDB(String name) throws SQLException {

        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(employeePayrollDBService.getEmployeePayrollData(name));

    }
//    public void writeEmployeeData(IOService ioService) {
//        if (ioService.equals(IOService.CONSOLE_IO))
//            System.out.println("Writing Employee Payroll Data to Console\n" + employeePayrollList);
//        else if (ioService.equals(IOService.FILE_IO))
//            new EmployeePayrollFileIOService().writeData(employeePayrollList);
//    }
//
//    public void printData(IOService ioService) {
//        new EmployeePayrollFileIOService().printData();
//    }
//    public long countEntries(IOService ioService) {
//        if (ioService.equals(IOService.FILE_IO))
//            return new EmployeePayrollFileIOService().countEntries();
//        return 0;
//    }
}
