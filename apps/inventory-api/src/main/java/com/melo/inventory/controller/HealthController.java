package com.melo.inventory.controller;

import com.melo.inventory.model.ApiInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public String health(){
        return "OK";
    }

    @GetMapping("/info")
    public ApiInfo apiInfo(){
        return new ApiInfo("running", "1.0.0");
    }
}
