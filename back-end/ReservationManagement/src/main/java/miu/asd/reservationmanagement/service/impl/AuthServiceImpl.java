package miu.asd.reservationmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.Constant;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.common.UserStatusEnum;
import miu.asd.reservationmanagement.configuration.SecurityConfig;
import miu.asd.reservationmanagement.dto.request.LoginRequestDto;
import miu.asd.reservationmanagement.dto.response.LoginResponseDto;
import miu.asd.reservationmanagement.exception.InvalidPasswordException;
import miu.asd.reservationmanagement.exception.NotFoundException;
import miu.asd.reservationmanagement.model.Customer;
import miu.asd.reservationmanagement.model.Employee;
import miu.asd.reservationmanagement.repository.CustomerRepository;
import miu.asd.reservationmanagement.repository.EmployeeRepository;
import miu.asd.reservationmanagement.service.AuthService;
import miu.asd.reservationmanagement.service.JwtService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SecurityConfig securityConfig;
    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(RoleEnum role, LoginRequestDto loginRequestDto) {
        String firstName = "";
        String lastName = "";
        RoleEnum loginRole = RoleEnum.CUSTOMER;
        if (role.equals(RoleEnum.CUSTOMER)) {
            Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumberAndStatus(
                    loginRequestDto.getPhoneNumber(),
                    UserStatusEnum.ACTIVE);
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                // check password
                if (!securityConfig.passwordEncoder()
                        .matches(loginRequestDto.getPassword(), customer.getPassword())) {
                    throw new InvalidPasswordException("Password is incorrect.");
                }
                firstName = customer.getFirstName();
                lastName = customer.getLastName();
                loginRole = customer.getRole().getRole();
            } else {
                throw new NotFoundException("Phone number not found.");
            }
        } else {
            Optional<Employee> optionalEmployee = employeeRepository.findByPhoneNumberAndStatus(
                    loginRequestDto.getPhoneNumber(),
                    UserStatusEnum.ACTIVE);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                // check password
                if (!securityConfig.passwordEncoder()
                        .matches(loginRequestDto.getPassword(), employee.getPassword())) {
                    throw new InvalidPasswordException("Password is incorrect.");
                }
                firstName = employee.getFirstName();
                lastName = employee.getLastName();
                loginRole = employee.getRole().getRole();
            } else {
                throw new NotFoundException("Phone number not found.");
            }
        }

        return LoginResponseDto.builder()
                .accessToken(createToken(loginRequestDto.getPhoneNumber(),
                        firstName + ' ' + lastName,
                                loginRole))
                .expiresIn(Constant.TOKEN_EXPIRATION_DURATION)
                .build();
    }

    private String createToken(String phoneNumber, String fullName, RoleEnum role) {
        return jwtService.generateToken(phoneNumber, fullName, role);
    }
}
