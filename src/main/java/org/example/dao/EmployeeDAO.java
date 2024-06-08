package org.example.dao;

import org.example.model.Employees;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\Hrapp1day8\\hr.db";
    private  static final  String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String SELECT_ONE_EMPLOYEE = "select * from employees where employee_id = ?";
    private static final String INSERT_EMPLOYEE = "insert into employees values (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    private static final String UPDATE_EMPLOYEE = "update employees set first_name = ?, last_name = ?, email = ? , phone_number = ? , hire_date = ? , job_id = ? , salary = ? , manager_id = ? , department_id = ?  where employee_id = ?";
    private static final String DELETE_EMPLOYEE = "delete from employees where employee_id = ?";


    public void INSERT_EMPLOYEE(Employees employee) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMPLOYEE);
        st.setInt(1, employee.getEmployeesId());
        st.setString(2, employee.getFirstName());
        st.setString(3, employee.getLastName());
        st.setString(4, employee.getEmail());
        st.setString(5, employee.getPhoneNumber());
        st.setString(6, employee.getHireDate());
        st.setDouble(7, employee.getSalary());
        st.executeUpdate();
    }

    public void UPDATE_EMPLOYEE(Employees employee) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEE);
        st.setInt(1, employee.getEmployeesId());
        st.setString(2, employee.getFirstName());
        st.setString(3, employee.getLastName());
        st.setString(4, employee.getEmail());
        st.setString(5, employee.getPhoneNumber());
        st.setString(6, employee.getHireDate());
        st.setDouble(7, employee.getSalary());
    }


    public void DELETE_EMPLOYEE(int employee_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEE);
        st.setInt(1, employee_id);
        st.executeUpdate();
    }

    public Employees selectEmployee(int employee_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEE);
        st.setInt(1, employee_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employees(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employees> SELECT_ALL_EMPLOYEES() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
        ResultSet rs = st.executeQuery();
        ArrayList<Employees> Employee = new ArrayList<>();
        while (rs.next()) {
            Employee.add(new Employees(rs));
        }

        return Employee;
    }
}