package org.example.Mapper;




import org.example.dto.EmployeesDto;
import org.example.model.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {JobMapper.class})
public interface  EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    EmployeesDto toEmpDto(Employees employee);

    Employees toModel(EmployeesDto dto);

}
