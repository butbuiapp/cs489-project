package miu.asd.reservationmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.common.UserStatusEnum;
import miu.asd.reservationmanagement.dto.EmployeeRequestDto;
import miu.asd.reservationmanagement.dto.EmployeeResponseDto;
import miu.asd.reservationmanagement.exception.NotFoundException;
import miu.asd.reservationmanagement.exception.RecordAlreadyExistsException;
import miu.asd.reservationmanagement.mapper.EmployeeMapper;
import miu.asd.reservationmanagement.model.Employee;
import miu.asd.reservationmanagement.model.Role;
import miu.asd.reservationmanagement.repository.EmployeeRepository;
import miu.asd.reservationmanagement.repository.RoleRepository;
import miu.asd.reservationmanagement.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;

    @Override
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> optionalEmployee =
                employeeRepository.findByPhoneNumberAndStatus(
                        employeeRequestDto.getPhoneNumber(),
                        UserStatusEnum.ACTIVE);
        if (optionalEmployee.isPresent()) {
            throw new RecordAlreadyExistsException("Phone number already used");
        }
        // map dto to entity
        Employee employee = EmployeeMapper.MAPPER.dtoToEntity(employeeRequestDto);
        employee.setStatus(UserStatusEnum.ACTIVE);
        // get role
        Role role = roleRepository.findByRole(employeeRequestDto.getRole());
        employee.setRole(role);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> optionalEmployee = findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            // find employee by phone number
            Optional<Employee> phoneNumber =
                    employeeRepository.findByPhoneNumberAndStatus(
                            employeeRequestDto.getPhoneNumber(),
                            UserStatusEnum.ACTIVE);
            if (phoneNumber.isPresent() &&
                    phoneNumber.get().getId() != existingEmployee.getId()) {
                throw new RecordAlreadyExistsException("Phone number already exists");
            } else {
                // update customer
                existingEmployee.setFirstName(employeeRequestDto.getFirstName());
                existingEmployee.setLastName(employeeRequestDto.getLastName());
                existingEmployee.setEmail(employeeRequestDto.getEmail());
                existingEmployee.setDob(employeeRequestDto.getDob());
                existingEmployee.setPhoneNumber(employeeRequestDto.getPhoneNumber());
                if (!existingEmployee.getRole().getRole().equals(employeeRequestDto.getRole())) {
                    // get role
                    Role role = roleRepository.findByRole(employeeRequestDto.getRole());
                    existingEmployee.setRole(role);
                }
                employeeRepository.save(existingEmployee);
            }
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setStatus(UserStatusEnum.DELETED);
            employeeRepository.save(employee);
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    @Override
    public List<EmployeeResponseDto> getActiveEmployees() {
        List<Employee> employees = employeeRepository.findAllByStatusIsOrderByLastName(UserStatusEnum.ACTIVE);
        return employees.stream().map(EmployeeMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = findById(id);
        if (optionalEmployee.isPresent()) {
            return EmployeeMapper.MAPPER.entityToDto(optionalEmployee.get());
        } else {
            throw new NotFoundException("Employee not found");
        }
    }

    private Optional<Employee> findById(Long id) {
        return employeeRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE);
    }
}
