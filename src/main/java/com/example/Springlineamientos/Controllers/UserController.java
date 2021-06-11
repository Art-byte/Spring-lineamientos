package com.example.Springlineamientos.Controllers;

import com.example.Springlineamientos.Entity.User;
import com.example.Springlineamientos.Repository.RoleRepository;
import com.example.Springlineamientos.Repository.UserRepository;
import com.example.Springlineamientos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    //Tenemos que mandar a llamar al repositorio y al servicio aqui

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/userform")
    public String userForm(Model model){
        model.addAttribute("userForm", new User());
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("listTab", "active");
        return "user-form/user-view";
    }
}
