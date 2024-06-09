package org.example.Mapper;




import org.example.dto.EmployeesDto;
import org.example.model.Employees;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper {

    EmployeesDto toEmpDto(Employees e);

    Employees toModel(EmployeesDto dto);
}
