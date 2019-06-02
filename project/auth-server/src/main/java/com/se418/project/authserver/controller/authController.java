package com.se418.project.authserver.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class authController {
    @GetMapping("/auth")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public String authenticate() {
        return "unauthorized";
    }

    @RequestMapping(value = "/users/get", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }
}
