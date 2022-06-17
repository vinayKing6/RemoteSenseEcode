package com.example.firstSpringBoot.web;

import com.example.firstSpringBoot.dao.UserRepository;
import com.example.firstSpringBoot.model.RegisterCheck;
import com.example.firstSpringBoot.dao.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;

@Slf4j
@Controller
public class HomeController {

    @Resource
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public HomeController(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/")
    public String home(Model model, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if (user!=null)
            model.addAttribute("user",user);
        return "index";
    }

    @GetMapping("/rest")
    public String rest(){
        return "restful";
    }

    @GetMapping("/login")
    public String getForm(Model model){
        return "login_up";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("register",true);
        return "login_up";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Errors errors, Model model){
        RegisterCheck check=new RegisterCheck(this.userRepository);

        if (check.checkForm(user)){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            log.info(user.toString());
            userRepository.save(user);
             model.addAttribute("hello",user);
            return "redirect:/login";
        }
        else{
            model.addAttribute("register",true);
            model.addAttribute("formCheck",!check.checkForm(user));
            model.addAttribute("checkTips",check.map);
            return "login_up";
        }

    }

    @GetMapping("/DeChange")
    public String DeChange(Model model, SessionStatus sessionStatus, @AuthenticationPrincipal User user){
        if (user!=null)
            model.addAttribute("user",user);
        return "DeChange";
    }

    @GetMapping("/GetTarget")
    public String GetTarget(Model model,SessionStatus sessionStatus,@AuthenticationPrincipal User user){
        if (user!=null)
            model.addAttribute("user",user);
        return "GetTarget";
    }

    @GetMapping("/DeTarget")
    public String DeTarget(Model model,SessionStatus sessionStatus,@AuthenticationPrincipal User user){
        if (user!=null)
            model.addAttribute("user",user);
        return "DeTarget";
    }

    @GetMapping("/ClassGeo")
    public String ClassGeo(Model model,SessionStatus sessionStatus,@AuthenticationPrincipal User user){
        if (user!=null)
            model.addAttribute("user",user);
        return "ClassGeo";
    }

}
