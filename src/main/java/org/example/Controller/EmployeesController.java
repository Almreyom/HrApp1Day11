package org.example.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;
import org.example.DAO.EmployeesDAO;
import org.example.FilterDTO.EMPSDDTO;
import org.example.FilterDTO.EmployeesFilterDTO;
import org.example.Model.Employees;

import java.net.URI;
import java.util.ArrayList;

@Path("/employees")
public class EmployeesController {

    EmployeesDAO dao = new EmployeesDAO();

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Employees> selectAllEmployees(@BeanParam EmployeesFilterDTO filter){
        try {

            return dao.selectAllEmps(filter);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{empId}")
    public Employees getEmployees(@PathParam("empId") int empId) {

        try {
            return dao.selectEmployees(empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{empId}")
    public void deleteEmployees(@PathParam("empId") int empId) {

        try {
            dao.deleteEmployees(empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @POST
    public void insertEmployees(Employees employees) {

        try {
            dao.insertEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PUT
    @Path("{empId}")
    public void updateEmployees(@PathParam("empId") int empId,Employees employees) {

        try {
            employees.setEmployeesId(empId);
            dao.updateEmployees(employees);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
