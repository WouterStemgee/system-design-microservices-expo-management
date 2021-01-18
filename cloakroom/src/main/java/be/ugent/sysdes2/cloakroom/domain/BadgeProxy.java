package be.ugent.sysdes2.cloakroom.domain;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BadgeProxy {
    @Value("${cloakroom.collaborators.badge.uri}")
    private String badgeUri;

    public Boolean updateBalance(int badgeId, float price) {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject request = new JSONObject();
        try {
            request.put("badgeId", badgeId);
            request.put("amount", price);
        }
        catch(JSONException e) {
            return false;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(badgeUri + badgeId + "/decrease", HttpMethod.POST, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }
}