package com.outlook.dev.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * 27.06.2017 by K.N.K
 *
 *
 */
public class UserRESTService {

    RestTemplate rest = new RestTemplate();

    public String get(String accessToken){
        String url = "https://graph.microsoft.com/v1.0/me/events";

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "java-tutorial");
        headers.set("client-request-id", UUID.randomUUID().toString());
        headers.set("return-client-request-id", "true");
        headers.set("Authorization", String.format("Bearer %s", accessToken));

//        Map<String, String> params = new HashMap<>();
//        params.put("$top", "15");

        HttpEntity entity = new HttpEntity(headers);

//        ResponseEntity<String> forEntity = rest.getForEntity(url, String.class, entity);
        ResponseEntity<String> exchange = rest.exchange(url, HttpMethod.GET, entity, String.class);


//        System.out.println("\n\n\n"+exchange.getBody()+"\n\n\n");



        return exchange.toString();

    }

}
