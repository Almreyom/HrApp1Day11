package org.example.DAO;

import org.example.Dto.EmployeesFilterDTO;
import org.example.Model.Employees;

import java.sql.*;
import java.util.ArrayList;


public class EmployeesDAO {
    private static final String URL =  "jdbc:sqlite:C:\\Users\\dev\\IdeaProjects\\Hrapp1day8\\hr.db";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String SELECT_EMPLOYEES_WITH_LOC = "select * from Employees where EmployessID = ?";
    private static final String SELECT_EMPLOYEES_WITH_LOC_PAGINATION = "select * from jobs where salary = ? order by EmployeeID limit ? offset ? ";
    private static final String SELECT_EMPLOYEES_WITH_PAGINATION = "select * from Employees order by EmployeeID limit ? offset ?";
    private static final String SELECT_ONE_EMPLOYEES = "select * from employees where employee_id = ?";
    private static final String INSERT_EMPLOYEES = "insert into employees values (?, ?, ?, ?, ?, ?,null, ?,null,null)";
    private static final String UPDATE_EMPLOYEES= "update employees set first_Name = ?, last_Name = ? ,email = ? ,Phone_number = ? ,Hire_date = ? ,Salary = ? where employee_id = ?";
    private static final String DELETE_EMPLOYEES = "delete from employees where employees_id = ?";

    public void insertEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_EMPLOYEES);
        st.setInt(1, e.getEmployeesId());
        st.setString(2, e.getFirstName());
        st.setString(3, e.getLastName());
        st.setString(4, e.getEmail());
        st.setString(5, e.getPhoneNumber());
        st.setString(6, e.getHireDate());
        st.setDouble(7, e.getSalary());

        st.executeUpdate();

    }

    public void updateEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEES);
        st.setString(1, e.getFirstName());
        st.setString(2, e.getLastName());
        st.setString(3, e.getEmail());
        st.setString(4, e.getPhoneNumber());
        st.setString(5, e.getHireDate());
        st.setDouble(6, e.getSalary());
        st.setInt(   7, e.getEmployeesId());

        st.executeUpdate();


    }

    public void deleteEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEES);
        st.setInt(1, employeeId);
        st.executeUpdate();
    }

    public Employees selectEmployees(int employeeId) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEES);
        st.setInt(1, employeeId);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Employees(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Employees> selectAllEmps(EmployeesFilterDTO filter) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st;
        if(filter.getSalary() != null && filter.getLimit()!= null){
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_LOC_PAGINATION);
            st.setDouble(1,filter.getSalary());
            st.setInt(2,filter.getLimit());
            st.setInt(3,filter.getOffset());
        } else if(filter.getSalary() != null) {
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_LOC);
            st.setDouble(1, filter.getSalary() );
        }
        else if(filter.getLimit() != null) {
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_PAGINATION);
            st.setInt(1, filter.getLimit());
            st.setInt(2, filter.getOffset());
        }
        else {
            st = conn.prepareStatement(SELECT_ALL_EMPLOYEES);
        }
        ResultSet rs = st.executeQuery();
        ArrayList<Employees> employees = new ArrayList<>();
        while (rs.next()) {
            employees.add(new Employees(rs));
        }

        return employees;
    }

}
