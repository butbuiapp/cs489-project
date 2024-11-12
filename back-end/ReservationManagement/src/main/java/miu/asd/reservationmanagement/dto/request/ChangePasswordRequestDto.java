package miu.asd.reservationmanagement.dto.request;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private String phoneNumber;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
