package com.fiipractic.twitterproject.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
@SecurityRequirement(name = "Bearer Authentication")
public class DemoController {

    @RequestMapping("")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}
