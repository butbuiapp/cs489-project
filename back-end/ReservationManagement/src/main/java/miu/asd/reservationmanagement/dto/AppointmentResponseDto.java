package miu.asd.reservationmanagement.dto;

import lombok.*;
import miu.asd.reservationmanagement.model.Invoice;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDto {
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private CustomerResponseDto customer;
    private EmployeeResponseDto technician;
    private String notes;
    private Invoice invoice;
}
