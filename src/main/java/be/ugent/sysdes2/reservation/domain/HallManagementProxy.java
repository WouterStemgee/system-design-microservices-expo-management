package be.ugent.sysdes2.reservation.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        Map<String, String> params = new HashMap<>();
        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("halls", Arrays.toString(halls.toArray(new String[0])));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.getForEntity(hallManagementUri + "availability", Void.class, params);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }

    public boolean reserve(ZonedDateTime dateStartDate, ZonedDateTime dateEndDate, List<String> halls) {
        Map<String, String> params = new HashMap<>();
        String startDate = dateStartDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String endDate = dateEndDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("halls", Arrays.toString(halls.toArray(new String[0])));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(hallManagementUri + "availability", params, Void.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }
}
