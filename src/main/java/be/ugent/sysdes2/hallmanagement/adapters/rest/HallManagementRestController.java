package be.ugent.sysdes2.hallmanagement.adapters.rest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.sysdes2.hallmanagement.domain.HallIdDoesNotExistsException;
import be.ugent.sysdes2.hallmanagement.domain.HallReservation;
import be.ugent.sysdes2.hallmanagement.domain.HallReservationRequest;
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

    @PostMapping("/reserve")
    public List<HallReservation> reserveHall(@RequestBody HallReservationRequest hallReservationRequest) {
        //check if hallids exists
        hallService.checkHallIds(hallReservationRequest.getHallIds());

        //check if date available
        hallService.datesAvailable(hallReservationRequest.getStartDate(), hallReservationRequest.getEndDate());

        return hallService.saveHallReservations(hallReservationRequest);
    }

    @GetMapping("/availability")
    public boolean getAvailability(@RequestParam("startDate") LocalDate startDate, @RequestParam("startDate") LocalDate endDate, @RequestParam("hall") Integer hallId) throws HallIdDoesNotExistsException {
        if(!hallService.checkHallId(hallId)) {
            throw new HallIdDoesNotExistsException("hall id: " + hallId);
        }

        return hallService.datesAvailable(startDate, endDate);
    }

}