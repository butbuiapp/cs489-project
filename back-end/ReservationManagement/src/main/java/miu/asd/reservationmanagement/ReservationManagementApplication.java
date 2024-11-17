package miu.asd.reservationmanagement;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.dto.request.EmployeeRequestDto;
import miu.asd.reservationmanagement.model.Employee;
import miu.asd.reservationmanagement.model.Role;
import miu.asd.reservationmanagement.repository.RoleRepository;
import miu.asd.reservationmanagement.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class ReservationManagementApplication {
	private final RoleRepository roleRepository;
	private final EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(ReservationManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			// create roles
//			Role r1 = new Role();
//			r1.setRole(RoleEnum.MANAGER);
//			roleRepository.save(r1);
//
//			Role r2 = new Role();
//			r2.setRole(RoleEnum.TECHNICIAN);
//			roleRepository.save(r2);
//
//			Role r3 = new Role();
//			r3.setRole(RoleEnum.CUSTOMER);
//			roleRepository.save(r3);
//
//			// create default manager
//			EmployeeRequestDto manager = new EmployeeRequestDto();
//			manager.setFirstName("But");
//			manager.setLastName("Bui");
//			manager.setPassword("12345678");
//			manager.setPhoneNumber("12345678");
//			manager.setRole(RoleEnum.MANAGER);
//			manager.setEmail("butbui@example.com");
//			employeeService.saveEmployee(manager);
		};
	}
}
