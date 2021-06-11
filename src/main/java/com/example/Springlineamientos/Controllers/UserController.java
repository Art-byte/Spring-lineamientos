package com.example.Springlineamientos.Controllers;

import com.example.Springlineamientos.Entity.User;
import com.example.Springlineamientos.Repository.RoleRepository;
import com.example.Springlineamientos.Repository.UserRepository;
import com.example.Springlineamientos.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @PostMapping("/userForm")
    public String createUser(@Valid @ModelAttribute("userForm") User user,
                             BindingResult result, ModelMap modelMap){
        if(result.hasErrors()){
            modelMap.addAttribute("userForm", user);
            modelMap.addAttribute("formTab", "active");
        }else{
            try{
                userService.createUser(user);
            }catch (Exception e){
                modelMap.addAttribute("formErrorMessage", e.getMessage());
                modelMap.addAttribute("userForm", user);
                modelMap.addAttribute("formTab", "active");
                modelMap.addAttribute("userList", userService.getAllUsers());
                modelMap.addAttribute("roles", roleRepository.findAll());
            }
        }
        modelMap.addAttribute("userList", userService.getAllUsers());
        modelMap.addAttribute("roles", roleRepository.findAll());
        return "user-form/user-view";
    }




}
