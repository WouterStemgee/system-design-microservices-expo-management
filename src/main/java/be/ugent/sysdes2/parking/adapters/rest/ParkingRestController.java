package be.ugent.sysdes2.parking.adapters.rest;

import be.ugent.sysdes2.parking.domain.ParkingFullException;
import be.ugent.sysdes2.parking.domain.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping("parking")
public class ParkingRestController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingRestController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping("/tickets/create")
    public void createTicket() throws ParkingFullException {
        parkingService.addParkingTicket();
    }

    @PostMapping("/tickets/validate/{id}")
    public void validateTicket(@PathVariable("id") Long ticketId) {
        parkingService.validateTicket(ticketId);
    }

    @PostMapping("/tickets/exit/{id}")
    public void disableTicket(@PathVariable("id") Long ticketId) {
        parkingService.exitParking(ticketId);
    }

    @GetMapping("/capacity")
    public void getCapacity(@RequestBody Map<String, String> payload) {
        String strStartDate = payload.get("startDate");
        String strEndDate = payload.get("endDate");
        int capacity = Integer.parseInt(payload.get("capacity"));
        LocalDate startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(strEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        parkingService.checkCapacity(startDate, endDate, capacity);
    }

    @PostMapping("/capacity")
    public void reserveCapacity(@RequestBody Map<String, String> payload) {
        String strStartDate = payload.get("startDate");
        String strEndDate = payload.get("endDate");
        int capacity = Integer.parseInt(payload.get("capacity"));
        LocalDate startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(strEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        parkingService.reserveCapacity(startDate, endDate, capacity);
    }
}
