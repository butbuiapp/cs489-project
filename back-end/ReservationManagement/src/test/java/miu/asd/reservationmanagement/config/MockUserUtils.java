package miu.asd.reservationmanagement.config;

import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.model.Employee;
import miu.asd.reservationmanagement.model.Role;

public class MockUserUtils {
    public static Employee getMockManager() {
        Employee manager = new Employee();
        manager.setFirstName("But");
        manager.setLastName("Bui");
        manager.setPassword("12345678");
        manager.setPhoneNumber("12345678");

        Role role = new Role();
        role.setRole(RoleEnum.MANAGER);
        manager.setRole(role);
        manager.setEmail("butbui@example.com");
        return manager;
    }
}
