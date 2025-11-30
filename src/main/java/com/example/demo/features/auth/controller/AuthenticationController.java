package com.example.demo.features.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.features.admin.dto.AdminLogin;
import com.example.demo.features.admin.service.AdminServices;
import com.example.demo.features.user.dto.UserLogin;
import com.example.demo.features.user.service.UserServices;

import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private AdminServices adminServices;

    @PostMapping("/adminLogin")
    public String adminLoginHandler(@ModelAttribute("adminLogin") AdminLogin login, Model model) {
        if (adminServices.validateAdminCredentials(login.getEmail(), login.getPassword())) {
            return "redirect:/admin/services";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }

    @PostMapping("/userLogin")
    public String userLoginHandler(@ModelAttribute("userLogin") UserLogin login, Model model) {
        String email = login.getUserEmail();
        String password = login.getUserPassword();

        if (userServices.validateLoginCredentials(email, password)) {
            /*
               on the original code, it would retrieve the user with its corresponding orders
               and call addAttribute on them.
               now we redirect to user/dashboard and put the logic on there
            */
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error2", "Invalid email or password");
            return "Login";
        }
    }
}