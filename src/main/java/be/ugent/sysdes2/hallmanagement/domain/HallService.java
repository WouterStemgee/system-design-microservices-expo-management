package be.ugent.sysdes2.hallmanagement.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.sysdes2.hallmanagement.persistence.HallRepository;
import be.ugent.sysdes2.hallmanagement.persistence.HallReservationRepository;

@Service
public class HallService {
    
    private HallRepository hallRepository;
    private HallReservationRepository hallReservationRepository;

    @Autowired
    public HallService(HallRepository hallRepository, HallReservationRepository hallReservationRepository) {
        this.hallRepository = hallRepository;
        this.hallReservationRepository = hallReservationRepository;
    }

    public boolean datesAvailable(LocalDate start, LocalDate end) throws DateNotAvailableException{
        if(hallReservationRepository.getBetweenDates(start, end).size() == 0) {
            return true;
        }

        throw new DateNotAvailableException("Dates are not available.");
    }

    //returns true if the hallId exists
    public boolean checkHallId(int hallId) {
        return hallRepository.findByHallId(hallId) == null;
    }

    public boolean checkHallIds(List<Integer> hallIds) throws HallIdDoesNotExistsException {
        String ids = "";
        for(int id : hallIds) {
            if(!this.checkHallId(id)) {
                ids += id + " ";
            }
        }
        if(ids == "") {
            throw new HallIdDoesNotExistsException("These ids do not exists: " + ids);
        }

        return true;
    }

    public List<HallReservation> saveHallReservations(HallReservationRequest req) {
        List<HallReservation> hallReservations = req.getHallReservations();
        for(HallReservation h : hallReservations) {
            hallReservationRepository.save(h);
        }

        return hallReservations;
    }
}