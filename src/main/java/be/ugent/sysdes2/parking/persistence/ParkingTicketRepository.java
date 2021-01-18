package be.ugent.sysdes2.parking.persistence;

import be.ugent.sysdes2.parking.domain.ParkingTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingTicketRepository extends CrudRepository<ParkingTicket, Long> {
    Optional<ParkingTicket> findById(@Param("ticket_id") Long parkingId);
}
