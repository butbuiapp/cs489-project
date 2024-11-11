package miu.asd.reservationmanagement.mapper;

import miu.asd.reservationmanagement.dto.EmployeeRequestDto;
import miu.asd.reservationmanagement.dto.EmployeeResponseDto;
import miu.asd.reservationmanagement.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "role.role", target = "role")
    EmployeeResponseDto entityToDto(Employee employee);
    Employee dtoToEntity(EmployeeRequestDto dto);
}
