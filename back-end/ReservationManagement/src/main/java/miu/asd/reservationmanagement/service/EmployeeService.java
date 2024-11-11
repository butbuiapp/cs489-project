package miu.asd.reservationmanagement.service;

import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.dto.EmployeeRequestDto;
import miu.asd.reservationmanagement.dto.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeRequestDto employeeRequestDto);
    void updateEmployee(Long id, EmployeeRequestDto employeeRequestDto);
    void deleteEmployeeById(Long id);
    List<EmployeeResponseDto> getActiveEmployees();
    EmployeeResponseDto getEmployeeById(Long id);
}
