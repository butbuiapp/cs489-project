package miu.asd.reservationmanagement.dto;

import lombok.*;
import miu.asd.reservationmanagement.model.Role;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dob;
    private String role;
}
