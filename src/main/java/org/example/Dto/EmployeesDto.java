package org.example.Dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDto {
    private int employeesId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private double salary;
    private ArrayList<LinkDto> links = new ArrayList<>();

    public EmployeesDto(){

    }

    public EmployeesDto(int employeesId, String firstName, String lastName, String email, String phoneNumber, String hireDate, double salary) {
        this.employeesId = employeesId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
    }
    public EmployeesDto(ResultSet rs)throws SQLException{
        employeesId = rs.getInt("employeesId");
        firstName = rs.getString("firstName");
        lastName = rs.getString("lastName");
        email = rs.getString("email");
        phoneNumber = rs.getString("phoneNumber");
        hireDate = rs.getString("hireDate");
        salary = rs.getDouble("salary");
    }

    public int getEmployeesId() {
        return employeesId;
    }

    public void setEmployeesId(int employeesId) {
        this.employeesId = employeesId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<LinkDto> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<LinkDto> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "EmployeesDto{" +
                "employeesId=" + employeesId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", salary=" + salary +
                ", links=" + links +
                '}';
    }
}
