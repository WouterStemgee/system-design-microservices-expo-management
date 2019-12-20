package be.ugent.sysdes2.parking.adapters.rest;

import be.ugent.sysdes2.parking.domain.ParkingFullException;
import be.ugent.sysdes2.parking.domain.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    public void getCapacity(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String capacity) {
        parkingService.checkCapacity(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels")),
                Integer.parseInt(capacity));
    }

    @PostMapping("/reserve")
    public void reserveCapacity(@RequestBody Map<String, String> payload) {
        String strStartDate = payload.get("startDate");
        String strEndDate = payload.get("endDate");
        int capacity = Integer.parseInt(payload.get("capacity"));
        ZonedDateTime startDate = LocalDate.parse(strStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        ZonedDateTime endDate = LocalDate.parse(strEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        parkingService.reserveCapacity(startDate, endDate, capacity);
    }
}
