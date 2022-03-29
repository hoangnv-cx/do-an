package com.hoang.travel.controller;
import com.hoang.travel.service.IUserService;
import com.hoang.travel.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private IUserService userService;
    @Autowired
    private SecurityUtils securityUtils;

    @RequestMapping(value = "/login")
    public String Login() {

        return "userGUI/userLogin";
    }
}
