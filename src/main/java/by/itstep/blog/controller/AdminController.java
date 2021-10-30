package by.itstep.blog.controller;

import by.itstep.blog.dto.admin.AdminSignInDto;
import by.itstep.blog.security.SecurityService;
import by.itstep.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/sign-in")
    public String getSignInForm(Model model) {
        AdminSignInDto request = new AdminSignInDto();
        model.addAttribute("request", request);
        return "sign-in";
    }

    @PostMapping("/sign-in")
    public String authorize(AdminSignInDto request) {
        if (!adminService.checkIfValid(request)) {
            return "redirect:/sign-in";
        }

        securityService.logIn();
        return "redirect:/index";
    }

}
