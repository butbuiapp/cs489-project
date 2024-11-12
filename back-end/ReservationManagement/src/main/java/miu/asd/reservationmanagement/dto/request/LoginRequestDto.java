package miu.asd.reservationmanagement.dto.request;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String phoneNumber;
    private String password;
}
