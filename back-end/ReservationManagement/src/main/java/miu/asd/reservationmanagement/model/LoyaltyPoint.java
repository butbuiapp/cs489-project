package miu.asd.reservationmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyPoint {
    private Integer earnedPoint;

    @Id
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
