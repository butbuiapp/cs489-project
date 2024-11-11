package miu.asd.reservationmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.common.UserStatusEnum;
import miu.asd.reservationmanagement.dto.CustomerRequestDto;
import miu.asd.reservationmanagement.dto.CustomerResponseDto;
import miu.asd.reservationmanagement.exception.NotFoundException;
import miu.asd.reservationmanagement.exception.RecordAlreadyExistsException;
import miu.asd.reservationmanagement.mapper.CustomerMapper;
import miu.asd.reservationmanagement.model.Customer;
import miu.asd.reservationmanagement.model.Role;
import miu.asd.reservationmanagement.repository.CustomerRepository;
import miu.asd.reservationmanagement.repository.RoleRepository;
import miu.asd.reservationmanagement.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public void saveCustomer(CustomerRequestDto customerRequestDto) {
        Optional<Customer> optionalCustomer =
                customerRepository.findByPhoneNumberAndStatus(
                        customerRequestDto.getPhoneNumber(),
                        UserStatusEnum.ACTIVE);
        if (optionalCustomer.isPresent()) {
            throw new RecordAlreadyExistsException("Phone number already used");
        }
        // map dto to entity
        Customer customer = CustomerMapper.MAPPER.dtoToEntity(customerRequestDto);
        customer.setStatus(UserStatusEnum.ACTIVE);
        // get role
        Role role = roleRepository.findByRole(RoleEnum.CUSTOMER);
        customer.setRole(role);
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Optional<Customer> optionalCustomer = findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            // find customer by phone number
            Optional<Customer> phoneNumber =
                    customerRepository.findByPhoneNumberAndStatus(
                            customerRequestDto.getPhoneNumber(),
                            UserStatusEnum.ACTIVE);
            if (phoneNumber.isPresent() &&
                    phoneNumber.get().getId() != existingCustomer.getId()) {
                throw new RecordAlreadyExistsException("Phone number already exists");
            } else {
                // update customer
                existingCustomer.setFirstName(customerRequestDto.getFirstName());
                existingCustomer.setLastName(customerRequestDto.getLastName());
                existingCustomer.setEmail(customerRequestDto.getEmail());
                existingCustomer.setDob(customerRequestDto.getDob());
                existingCustomer.setPhoneNumber(customerRequestDto.getPhoneNumber());
                customerRepository.save(existingCustomer);
            }
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public void deleteCustomerById(Long id) {
        Optional<Customer> optionalCustomer = findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setStatus(UserStatusEnum.DELETED);
            customerRepository.save(customer);
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public List<CustomerResponseDto> getActiveCustomers() {
        List<Customer> customers = customerRepository.findAllByStatusIsOrderByLastName(UserStatusEnum.ACTIVE);
        return customers.stream().map(CustomerMapper.MAPPER::entityToDto).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = findById(id);
        if (optionalCustomer.isPresent()) {
            return CustomerMapper.MAPPER.entityToDto(optionalCustomer.get());
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerResponseDto getCustomerByPhone(String phoneNumber) {
        Optional<Customer> optionalCustomer =
                customerRepository.findByPhoneNumberAndStatus(phoneNumber, UserStatusEnum.ACTIVE);
        if (optionalCustomer.isPresent()) {
            return CustomerMapper.MAPPER.entityToDto(optionalCustomer.get());
        } else {
            throw new NotFoundException("Customer not found");
        }
    }

    private Optional<Customer> findById(Long id) {
        return customerRepository.findByIdAndStatus(id, UserStatusEnum.ACTIVE);
    }
}
