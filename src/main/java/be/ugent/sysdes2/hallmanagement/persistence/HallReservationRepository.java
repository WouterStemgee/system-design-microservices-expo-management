package be.ugent.sysdes2.hallmanagement.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.sysdes2.hallmanagement.domain.HallReservation;

@Repository
public interface HallReservationRepository extends CrudRepository<HallReservation, Integer>  {
    @Query("SELECT hallreservation FROM reservations WHERE startDate >= ?1 AND endDate <= ?2")
    public List<HallReservation> getBetweenDates(LocalDate startDate, LocalDate endDate);
}