package miu.asd.reservationmanagement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
  private final String tokenType = "Bearer";
  private String accessToken;
  private Long expiresIn;
}
