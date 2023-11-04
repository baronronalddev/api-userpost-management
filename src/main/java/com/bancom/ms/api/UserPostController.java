package com.bancom.ms.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPostController {

    @GetMapping("/api")
    public String message() {
        return "Hola Virtual!";
    }


}
