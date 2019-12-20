package be.ugent.sysdes2.reservation.adapters.rest;

import be.ugent.sysdes2.reservation.domain.ReservationException;
import be.ugent.sysdes2.reservation.domain.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("reservation")
public class ReservationRestController {

    private static final Logger log = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/availability")
    public void getAvailability(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String capacity, @RequestParam String[] halls) throws JsonProcessingException {
        reservationService.getAvailability(
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels")),
                Integer.parseInt(capacity),
                Arrays.asList(halls));
    }

    @PostMapping("/create")
    public void createReservation(@RequestBody Map<String, String> payload) throws JsonProcessingException, JSONException {
        ZonedDateTime startDate = LocalDate.parse(payload.get("startDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        ZonedDateTime endDate = LocalDate.parse(payload.get("endDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        int capacity = Integer.parseInt(payload.get("capacity"));
        List<String> halls = Arrays.asList(new ObjectMapper().readValue(payload.get("halls"), String[].class)); // needs testing with Hall Management
        int maxVisitors = Integer.parseInt(payload.get("maxVisitors"));
        float ticketPrice = Float.parseFloat(payload.get("ticketPrice"));
        reservationService.reserve(startDate, endDate, capacity, halls, maxVisitors, ticketPrice);
    }









}
