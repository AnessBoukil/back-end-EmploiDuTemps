package com.emploi.emploiapplication.web;

import com.emploi.emploiapplication.entities.Admin;
import com.emploi.emploiapplication.services.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/admin")
@RestController
@Slf4j
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    @GetMapping("/getByUsername")
    public Admin getAdminByUsername(@RequestParam String username) {
        return adminService.getAdmin(username);
    }

}
