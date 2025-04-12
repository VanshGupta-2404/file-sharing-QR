package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heath-check")
public class Health_Check {

    @GetMapping
    public String healthcheck(){
        return "Good Work";
    }
}
