package com.cubixedu.vt.vtdemo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class VtController {

    private final RestClient restClient;
    
    public VtController(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8081").build();
    }
}
