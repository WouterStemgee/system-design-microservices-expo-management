package be.ugent.sysdes2.hallmanagement.domain;

import java.time.LocalDate;

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

    public boolean datesAvailable(LocalDate start, LocalDate end) {
        return hallReservationRepository.getBetweenDates(start, end).size() == 0;
    }
}