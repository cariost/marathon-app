package com.rzerosystems.marathonapp.service;

import com.rzerosystems.marathonapp.model.Marathon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarathonServiceImpl implements MarathonService {

    private RestTemplate restTemplate;

    // inject value from application.properties
    @Value("${api.marathon.url}")
    private String API_URL;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Marathon> getMarathons() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL);

        // create headers
        HttpHeaders headers = new HttpHeaders();

// set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

// example of custom header
        headers.set("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOiJiMTAwODMxZC00NjgzLTQ0NWUtYjZiZC0wMmMzY2ZhMTZmZGEifQ.-UU5qRh1uDuKo7sFEtGYxplUO57BhRey5e0eA722xOM");

// build the request
        HttpEntity request = new HttpEntity(headers);


/*        return restTemplate.exchange(
                API_URL,
                HttpMethod.GET,
                request,
                MarathonData.class
        ).getBody().getMarathonEvent();*/

        Marathon[] marathons = restTemplate.getForObject(uriBuilder.toUriString(), Marathon[].class);
        return Arrays.stream(marathons).collect(Collectors.toList());
    }
}
