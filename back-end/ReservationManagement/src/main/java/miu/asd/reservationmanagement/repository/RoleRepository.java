package miu.asd.reservationmanagement.repository;

import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(RoleEnum roleEnum);
}
