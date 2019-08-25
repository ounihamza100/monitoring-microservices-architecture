package com.dashboard;

import com.dashboard.constants.Constatnts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * Created by haithem.ben-chaaben on 8/25/2019.
 */
@Configuration
public class ClassConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(Constatnts.MEDIA_TYPE_Value));
        return headers;
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
