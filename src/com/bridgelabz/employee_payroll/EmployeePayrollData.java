package com.bridgelabz.employee_payroll;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class EmployeePayrollData {
    int id;
    String name;
    double salary;
    LocalDate start;

    public EmployeePayrollData(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public EmployeePayrollData(int id, String name, double salary, LocalDate start) {
        this(id, name, salary);
        this.start = start;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id && Double.compare(that.salary, salary) == 0 && name.equals(that.name);
    }


}
