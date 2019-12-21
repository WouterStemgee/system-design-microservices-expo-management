package be.ugent.sysdes2.reservation.adapters.rest;


import be.ugent.sysdes2.reservation.domain.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@CrossOrigin
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
    public void createReservation(@RequestBody Map<String, Object> payload) throws JsonProcessingException, JSONException {
        ZonedDateTime startDate = LocalDate.parse((String) payload.get("startDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        ZonedDateTime endDate = LocalDate.parse((String) payload.get("endDate"), DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay(ZoneId.of("Europe/Brussels"));
        int capacity = Integer.parseInt((String) payload.get("capacity"));
        JSONArray jsonHalls = new JSONArray(String.valueOf(payload.get("halls")));
        List<String> halls = new ArrayList<>();
        for (int i = 0; i < jsonHalls.length(); i++) {
            halls.add(jsonHalls.getString(i));
        }
        int maxVisitors = Integer.parseInt((String) payload.get("maxVisitors"));
        float ticketPrice = Float.parseFloat((String) payload.get("ticketPrice"));
        reservationService.reserve(startDate, endDate, capacity, halls, maxVisitors, ticketPrice);
    }









}
