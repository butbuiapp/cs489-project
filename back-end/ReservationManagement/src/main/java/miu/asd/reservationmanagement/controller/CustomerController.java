package miu.asd.reservationmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.dto.CustomerRequestDto;
import miu.asd.reservationmanagement.dto.CustomerResponseDto;
import miu.asd.reservationmanagement.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constant.CUSTOMER_URL)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        customerService.saveCustomer(customerRequestDto);
        return ResponseEntity.ok().body("Customer created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        customerService.updateCustomer(id, customerRequestDto);
        return ResponseEntity.ok().body("Customer updated successfully");
    }

    @GetMapping
    public ResponseEntity<?> getActiveCustomers() {
        List<CustomerResponseDto> customers = customerService.getActiveCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        CustomerResponseDto customerDto = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/search/{phoneNumber}")
    public ResponseEntity<?> getCustomerByPhone(@PathVariable String phoneNumber) {
        CustomerResponseDto customerDto = customerService.getCustomerByPhone(phoneNumber);
        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().body("Customer deleted successfully");
    }

}
