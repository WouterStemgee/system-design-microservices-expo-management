package be.ugent.sysdes2.hallmanagement.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.sysdes2.hallmanagement.domain.HallReservation;
import be.ugent.sysdes2.hallmanagement.domain.HallService;
import be.ugent.sysdes2.hallmanagement.persistence.HallRepository;
import be.ugent.sysdes2.hallmanagement.persistence.HallReservationRepository;

@RestController
@RequestMapping("/hall-management")
public class HallManagementRestController {
    private static Logger logger = LoggerFactory.getLogger(HallManagementRestController.class);

    private HallReservationRepository hallReservationRepository;
    private HallRepository hallRepository;
    private HallService hallService;

    @Autowired
    private HallManagementRestController(HallReservationRepository hallReservationRepository, HallRepository hallRepository, HallService hallService) {
        this.hallReservationRepository = hallReservationRepository;
        this.hallRepository = hallRepository;
        this.hallService = hallService;
    }

    @PostMapping()
    public Object reserveHall(@RequestBody HallReservation hallReservation) {
        //check if hallid exists
        if(hallRepository.findByHallId(hallReservation.getHallId()) == null) {
            return "No hall with that id";
        }

        //check if date available
        if(!hallService.datesAvailable(hallReservation.getStartDate(), hallReservation.getEndDate())) {
            return "Date already reserved";
        }

        return hallReservationRepository.save(hallReservation);
    }
}