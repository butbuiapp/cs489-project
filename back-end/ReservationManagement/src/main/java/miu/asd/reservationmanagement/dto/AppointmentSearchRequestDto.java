package miu.asd.reservationmanagement.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentSearchRequestDto {
    LocalDate appointmentDate;
}
