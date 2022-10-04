package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexeev.pr.models.Role;
import ru.alexeev.pr.models.User;
import ru.alexeev.pr.repository.UserRepository;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String userView(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "user/user-main";
    }

    @GetMapping("/{id}")
    public String userViewUpdate(@PathVariable Long id,
            Model model){
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("rolesusers", Role.values());
        return "user/user-details";
    }

    @PostMapping("/{id}")
    public String userUpdate(@PathVariable Long id,
                             @RequestParam(name = "rolesuser[]", required = false) String[] roles,
                             Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.getRoles().clear();

        for (String role:
             roles) {
            user.getRoles().add(Role.valueOf(role));
        }
        //==
        //Arrays.stream(roles).forEach(r->user.getRoles().add(Role.valueOf(r)));

        userRepository.save(user);
        return "redirect:/user/";
    }

}
