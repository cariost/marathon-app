package com.rzerosystems.marathonapp.service;

import com.rzerosystems.marathonapp.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService{

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // inject value from application.properties
    @Value("${api.result.url}")
    private String API_URL;
    @Override
    public List<Result> getResults() {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL);

        Result[] results = restTemplate.getForObject(uriBuilder.toUriString(), Result[].class);
        return Arrays.stream(results).collect(Collectors.toList());
    }

    @Override
    public Result getResultById(int identifier) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL + "/" + identifier);

        return restTemplate.getForObject(uriBuilder.toUriString(), Result.class);
    }

    @Override
    public Result save(Result result) {
        return restTemplate.postForObject(API_URL, result, Result.class);
    }

    @Override
    public void update(int identifier, Result result) {
        String url = API_URL + "/" + identifier;
        restTemplate.put(url, result, identifier);
    }

    @Override
    public void delete(int identifier) {
        String url = API_URL + "/" + identifier;
        restTemplate.delete(url, identifier);
    }
}
