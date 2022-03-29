package com.hoang.travel.controller;


import com.hoang.travel.entity.RoleEntity;
import com.hoang.travel.entity.UserEntity;
import com.hoang.travel.service.IRoleService;
import com.hoang.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginUserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @RequestMapping("/login2")
    public String login() {
        return "userGUI/userLogin";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(Model model) {
        model.addAttribute("roleList", roleService.findAllRole());
        model.addAttribute("users", new UserEntity());
        return "userGUI/userSignin";
    }
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String signin(@Valid @ModelAttribute("users") UserEntity user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "userGUI/userSignin";
        }
        if (userService.userNameExists(user.getUserName())) {
            bindingResult.addError(new FieldError("users", "userName", "Tên đăng nhấp đã được sử dụng"));
            return "userGUI/userSignin";
        }
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        user.setCreatedAt(timestamp);
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setId(3);
        List<RoleEntity> roleEntities=new ArrayList<>();
        roleEntities.add(roleEntity);
        user.setRoles(roleEntities);
        userService.addAndUpdateUser(user);
        return "redirect:/login2";
    }
}
