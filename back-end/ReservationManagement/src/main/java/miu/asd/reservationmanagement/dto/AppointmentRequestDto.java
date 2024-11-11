package miu.asd.reservationmanagement.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDto {
    private LocalDate date;
    private LocalTime time;
    private CustomerRequestDto customer;
    private EmployeeRequestDto technician;
    private String notes;
    private InvoiceRequestDto invoice;
}
