package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.services.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

    private UserAccountService userAccountService;

    @PostMapping(path = "/login")
    public Map<String, String> login(@RequestBody Map<String, String> requestMap) {
            return userAccountService.login(requestMap);
    }

}
