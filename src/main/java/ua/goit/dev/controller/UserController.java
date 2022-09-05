package ua.goit.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.dev.model.dao.Role;
import ua.goit.dev.model.dao.User;
import ua.goit.dev.service.RoleService;
import ua.goit.dev.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String viewListOfUsers(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getAllDto());
        User byEmail = userService.getByEmail(authentication.getName());
        model.addAttribute("userByEmail", byEmail);
        return "users";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("users", new User());
        List<Role> all = roleService.getAll();
        model.addAttribute("allRoles", all);
        return "addFormUser";
    }

    @PostMapping(path = "/new")
    public String addOrUpdateUser(@ModelAttribute("users") User user) {

        User build = User.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole()).build();

        userService.createOrUpdate(build);
        return "redirect:/user";
    }

    @GetMapping("removeA/{id}")
    public String delete(@PathVariable UUID id,Authentication authentication){
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        if(user.getEmail().equals(authentication.getName())){
            return "redirect:/user";
        }else{
            userService.deleteById(id);
        }

        return "redirect:/user";
    }
    @GetMapping("/editA/{id}")
    public String showUpdateForm(@PathVariable("id") UUID id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        List<Role> all = roleService.getAll();
        model.addAttribute("allRoles", all);
        model.addAttribute("user", user);
        return "updateUser";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") UUID id, @ModelAttribute("user") User user) {
         User user1 = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        userService.createOrUpdate(user1);
        return "redirect:/user";
    }
    @GetMapping("/editU/{id}")
    public String showUpdateFormUser(@PathVariable("id") UUID id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "updateUserU";
    }
    @PostMapping("/updateUser/{id}")
    public String update(@PathVariable("id") UUID id, @ModelAttribute("user") User user) {
        User user1 = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createOrUpdate(user1);
        return "redirect:/user";
    }
}
