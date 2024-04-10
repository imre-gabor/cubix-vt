package com.cubixedu.vt.vtdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class VtController {

    private Logger logger = LoggerFactory.getLogger(VtController.class);
    private final RestClient restClient;

    public VtController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8081").build();
    }


    @GetMapping("/api/callBlocking/{seconds}")
    public String callBlocking (@PathVariable int seconds){
        ResponseEntity<Void> response = restClient.get()
                .uri("block/" + seconds)
                .retrieve()
                .toBodilessEntity();
        logger.info("Got response with status {} on {}", response.getStatusCode().value(), Thread.currentThread().getName());
        return Thread.currentThread().getName();
    }
}
