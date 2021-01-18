package be.ugent.sysdes2.reservation.domain;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HallManagementProxy {

    @Value("${reservation.collaborators.hallmanagement.uri}")
    private String hallManagementUri;

    public boolean getAvailability(ZonedDateTime dateStartDate, ZonedDateTime dateEndDate, List<String> halls) {
        RestTemplate restTemplate = new RestTemplate();

        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(hallManagementUri + "availability")
                .queryParam("startDate", startDate)
                .queryParam("endDate", endDate)
                .queryParam("halls", Arrays.toString(halls.toArray(new String[0])));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }

    public boolean reserve(ZonedDateTime dateStartDate, ZonedDateTime dateEndDate, List<String> halls) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        JSONObject request = new JSONObject();
        request.put("startDate", startDate);
        request.put("endDate", endDate);
        request.put("halls", Arrays.toString(halls.toArray(new String[0])));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(hallManagementUri + "reserve", HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }
}
