package com.cubixedu.vt.remote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DelayController {

    @GetMapping("/block/{seconds}")
    public String delay(@PathVariable int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Slept for %d ".formatted(seconds);
    }
}

