package be.ugent.sysdes2.parking.persistence;

import be.ugent.sysdes2.parking.domain.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends CrudRepository<Parking, Long> {
    List<Parking> findAll();
}
