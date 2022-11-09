package com.homework1.server.model;

import java.util.Objects;

/**
 * @author berklimoncu
 */
public class Employee {
    private Long employeeId;
    private String employeeName;
    private Double monthlySalary;

    public Employee() {
    }

    public Employee(String employeeName, Double monthlySalary) {
        this.employeeName = employeeName;
        this.monthlySalary = monthlySalary;
    }

    public Employee(Long employeeId, String employeeName, Double monthlySalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.monthlySalary = monthlySalary;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", monthlySalary=" + monthlySalary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId) && Objects.equals(employeeName, employee.employeeName) && Objects.equals(monthlySalary, employee.monthlySalary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, employeeName, monthlySalary);
    }
}
