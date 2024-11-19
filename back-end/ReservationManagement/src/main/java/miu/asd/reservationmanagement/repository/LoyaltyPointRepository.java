package miu.asd.reservationmanagement.repository;

import miu.asd.reservationmanagement.model.LoyaltyPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPointRepository extends JpaRepository<LoyaltyPoint, Long> {
}
