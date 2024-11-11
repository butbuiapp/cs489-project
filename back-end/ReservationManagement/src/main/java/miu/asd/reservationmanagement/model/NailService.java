package miu.asd.reservationmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.asd.reservationmanagement.common.ServiceStatusEnum;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "service")
public class NailService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;
    private Double price;
    private Integer duration;
    private String description;

    @Enumerated(EnumType.STRING)
    private ServiceStatusEnum status;
}
