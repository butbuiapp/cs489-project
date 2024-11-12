package miu.asd.reservationmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.dto.request.ChangePasswordRequestDto;
import miu.asd.reservationmanagement.dto.request.EmployeeRequestDto;
import miu.asd.reservationmanagement.dto.response.EmployeeResponseDto;
import miu.asd.reservationmanagement.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.EMPLOYEE_URL)
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.saveEmployee(employeeRequestDto);
        return ResponseEntity.ok().body(Map.of("message", "Employee created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.updateEmployee(id, employeeRequestDto);
        return ResponseEntity.ok().body(Map.of("message", "Employee updated successfully"));
    }

    @GetMapping
    public ResponseEntity<?> getActiveEmployees() {
        List<EmployeeResponseDto> employees = employeeService.getActiveEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        EmployeeResponseDto employeeResponseDto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok().body(employeeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body(Map.of("message", "Employee deleted successfully"));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        employeeService.changePassword(id, changePasswordRequestDto);
        return ResponseEntity.ok().body(Map.of("message", "Password changed successfully"));
    }

}
