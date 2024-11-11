package miu.asd.reservationmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.asd.reservationmanagement.common.AppointmentStatusEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Employee technician;
    private String notes;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @Enumerated(EnumType.STRING)
    private AppointmentStatusEnum status;
}
