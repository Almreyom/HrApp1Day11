package org.example.FilterDTO;

import jakarta.ws.rs.QueryParam;

public class EmployeesFilterDTO {
    private @QueryParam("salary") Double salary;
    private @QueryParam("limit") Integer limit;
    private @QueryParam("offset") int offset;

    public Double getSalary() {
        return salary;
    }


    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}


