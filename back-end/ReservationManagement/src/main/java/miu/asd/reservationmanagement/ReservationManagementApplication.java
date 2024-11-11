package miu.asd.reservationmanagement;

import lombok.RequiredArgsConstructor;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.model.Role;
import miu.asd.reservationmanagement.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class ReservationManagementApplication {
	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ReservationManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
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
		};
	}
}
