package com.bridgelabz.employee_payroll;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.bridgelabz.employee_payroll.EmployeePayrollService.IOService.DB_IO;
import static org.junit.Assert.*;

public class EmployeePayrollDataTest {

    @Test
    public void givenEmployeePayrollInDB_WhenRetriveShouldMatchTheCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollService.readEmployeeData(DB_IO);
        Assert.assertEquals(3, employeePayrollDataList.size());
    }

}