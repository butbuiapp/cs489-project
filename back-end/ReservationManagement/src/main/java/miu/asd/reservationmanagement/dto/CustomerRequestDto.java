package miu.asd.reservationmanagement.dto;

import lombok.Getter;
import lombok.Setter;
import miu.asd.reservationmanagement.common.UserStatusEnum;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerRequestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private LocalDate dob;
    private String password;
}
