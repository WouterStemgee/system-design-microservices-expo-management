package be.ugent.sysdes2.reservation.domain;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingProxy {

    @Value("${reservation.collaborators.parking.uri}")
    private String parkingUri;

    public boolean getCapacity(ZonedDateTime dateStartDate, ZonedDateTime dateEndDate, int capacity) {
        RestTemplate restTemplate = new RestTemplate();

        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(parkingUri + "capacity")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("capacity", capacity);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }

    public boolean reserve(ZonedDateTime dateStartDate, ZonedDateTime dateEndDate, int capacity) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        JSONObject request = new JSONObject();
        request.put("startDate", startDate);
        request.put("endDate", endDate);
        request.put("capacity", Integer.toString(capacity));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(parkingUri + "reserve", HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }
}
