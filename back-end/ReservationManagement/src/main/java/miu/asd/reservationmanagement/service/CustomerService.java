package miu.asd.reservationmanagement.service;

import miu.asd.reservationmanagement.dto.CustomerRequestDto;
import miu.asd.reservationmanagement.dto.CustomerResponseDto;
import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerRequestDto customerRequestDto);
    void updateCustomer(Long id, CustomerRequestDto customerRequestDto);
    void deleteCustomerById(Long id);
    List<CustomerResponseDto> getActiveCustomers();
    CustomerResponseDto getCustomerById(Long id);
    CustomerResponseDto getCustomerByPhone(String phoneNumber);
}
