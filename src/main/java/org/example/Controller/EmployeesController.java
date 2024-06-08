package org.example.Controller;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.DAO.EmployeesDAO;
import org.example.Dto.EmployeesDto;
import org.example.Exceptions.DataNotFoundException;
import org.example.Dto.EmployeesFilterDTO;
import org.example.Model.Employees;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/employees")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
public class EmployeesController {

    EmployeesDAO dao = new EmployeesDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    public Response selectAllEmployees(@BeanParam EmployeesFilterDTO filter){
        try {
            GenericEntity<ArrayList<Employees>> employees = new GenericEntity<ArrayList<Employees>>(dao.SELECT_ALL_EMPLOYEES(filter.getSalary())) {};
            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(employees)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            else if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf("text/csv"))) {
                return Response
                        .ok(employees)
                        .type("text/csv")
                        .build();
            }
            return Response
                    .ok(employees, MediaType.APPLICATION_JSON)
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

//    @GET
//    @Path("{empId}")
//    public Employees getEmployees(@PathParam("empId") int empId) {
//
//        try {
//            return dao.selectEmployees(empId);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GET
    @Path("{job_id}")
    public Response SELECT_ONE_EMPLOYEES(@PathParam("EmployeeId") int EmployeesId)throws SQLException {

        try {
            Employees employees = dao.selectEmployees(EmployeesId);
            if(employees == null ){

                throw new DataNotFoundException("Employees " + EmployeesId + "Not found");
            }
            //headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML) {

            EmployeesDto dto = new EmployeesDto();
            dto.setEmployeesId(Employees.getEmployeesId());
            dto.setFirstName(employees.getFirstName());
            dto.setLastName(employees.getLastName());
            dto.setEmail(employees.getEmail());
            dto.setHireDate(employees.getHireDate());
            dto.setSalary(employees.getSalary());
           // addLinks(dto);
            return Response.ok(dto).build();
            /* return Response
                    .ok(dto)
                    .type(MediaType.APPLICATION_JSON)
                    .build(); */

        } catch (ClassNotFoundException  e) {
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
    public Response insertEmployees(Employees employees) {

        try {
            dao.insertEmployees(employees);
            return Response
                    .ok(employees)
                    .status(Response.Status.CREATED)
                    .build();
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
