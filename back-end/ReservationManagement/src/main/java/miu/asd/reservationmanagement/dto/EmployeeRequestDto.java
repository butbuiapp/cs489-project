package miu.asd.reservationmanagement.dto;

import lombok.*;
import miu.asd.reservationmanagement.common.RoleEnum;
import miu.asd.reservationmanagement.common.UserStatusEnum;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dob;
    private String password;
    private RoleEnum role;
}
