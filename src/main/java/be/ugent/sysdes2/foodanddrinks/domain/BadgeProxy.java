package be.ugent.sysdes2.foodanddrinks.domain;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class BadgeProxy {

    @Value("${foodanddrinks.collaborators.badge.uri}")
    private String badgeUri;

    public ResponseEntity<String> decreaseBalance(Transaction transaction) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type",MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("badgeId", String.valueOf(transaction.getBadgeId()));

        URI uri = UriComponentsBuilder.fromUriString(badgeUri + "{badgeId}/decrease").buildAndExpand(urlParams).toUri();

        JSONObject request = new JSONObject();
        request.put("amount", transaction.getAmount());

        HttpEntity<String> entity = new HttpEntity<>(request.toString(),headers);

        return restTemplate.exchange(uri, HttpMethod.PUT, entity, String.class);
    }
}
