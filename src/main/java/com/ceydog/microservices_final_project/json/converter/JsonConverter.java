package com.ceydog.microservices_final_project.json.converter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JsonConverter {
    public static String getJSONBodyFromURL(String url){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseString = responseEntity.getBody();

        return responseString;
    }

}
