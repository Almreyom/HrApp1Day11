package org.example.dao;

import org.example.dto.EmployeeFilterDto;
import org.example.model.Employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class EmployeeDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\Hrapp1day9\\src\\main\\java\\org\\example\\hr.db";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees";
    private static final String SELECT_ONE_JOBS_JOIN_EMPLOYEES = "select * from jobs join employees on job.employees_id = employees.employees_id where job_id = ?";
    private static final String SELECT_ONE_EMPLOYEES = "select * from employees where employee_id = ?";
    private static final String INSERT_EMPLOYEES = "insert into employees values (?, ?, ?,?,?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEES= "update employees set first_name = ?, last_name = ? ,email = ? ,phone_number = ? ,hire_date = ? ,job_id = ?,Salary = ? ,manager_id = ? ,department_id = ? where employee_id = ?";
    private static final String DELETE_EMPLOYEES = "delete from employees where employee_id = ?";



    private static final String SELECT_EMPLOYEES_WITH_ID = "select * from employees where employee_id = ?";
    private static final String SELECT_EMPLOYEES_WITH_ID_PAGINATION = "select * from employees where employee_id = ? order by employee_id limit ? offset ?";
    private static final String SELECT_EMPLOYEES_WITH_PAGINATION = "select * from employees order by min_salary limit ? employee_id ?";
    public EmployeeDAO() {
    }

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
        st.setInt(7, e.getJob_id());
        st.setDouble(8, e.getSalary());
        st.setInt(9, e.getManger_id());
        st.setInt(10, e.getDepartment_id());
        st.executeUpdate();
    }

    public void updateEmployees(Employees e) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_EMPLOYEES);
        st.setInt(1, e.getEmployeesId());
        st.setString(2, e.getFirstName());
        st.setString(3, e.getLastName());
        st.setString(4, e.getEmail());
        st.setString(5, e.getPhoneNumber());
        st.setString(6, e.getHireDate());
        st.setInt(7, e.getJob_id());
        st.setDouble(8, e.getSalary());
        st.setInt(9, e.getManger_id());
        st.setInt(10, e.getDepartment_id());
        st.executeUpdate();
    }

    public void deleteEmployees(int employee_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_EMPLOYEES);
        st.setInt(1, employee_id);
        st.executeUpdate();
    }

    public Employees selectEmployees(int employee_id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_EMPLOYEES);
        st.setInt(1, employee_id);
        ResultSet rs = st.executeQuery();
        return rs.next() ? new Employees(rs) : null;
    }

    public ArrayList<Employees> selectAllEmployees(EmployeeFilterDto filterDto) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st ;
        if (filterDto != null && filterDto != null){
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_ID_PAGINATION);
            st.setDouble(1,filterDto.getSalary());
            st.setDouble(2,filterDto.getLimit());
        } else if (0 != filterDto.getOffset()) {
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_ID);
            st.setDouble(1,filterDto.getSalary());
        } else if (filterDto.getLimit()!=null) {
            st = conn.prepareStatement(SELECT_EMPLOYEES_WITH_PAGINATION);
            st.setInt(1,filterDto.getLimit());
            st.setInt(2,filterDto.getOffset());}
        else{
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