package com.rzerosystems.marathonapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    private HeaderInterceptor actionTrackInterceptor;
    private RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    @Autowired
    public void setActionTrackInterceptor(HeaderInterceptor actionTrackInterceptor) {
        this.actionTrackInterceptor = actionTrackInterceptor;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(actionTrackInterceptor));
        restTemplate.setMessageConverters(Collections.singletonList(mappingJackson2HttpMessageConverter()));
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> types = Arrays.asList(
                new MediaType("text", "plain", Charset.defaultCharset()),
                new MediaType("application", "json", Charset.defaultCharset()),
                new MediaType("application", "*+json", Charset.defaultCharset())
        );
        converter.setSupportedMediaTypes(types);
        return converter;
    }
}