package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.converters.StringArrayToRoleConverter;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StringArrayToRoleConverter roleConverter;

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        return "userId";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam(value = "roless", required = false) String[] roles) {
        user.setRoles(roleConverter.convert(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "roless", required = false) String[] roles, @PathVariable("id") long id) {
        user.setRoles(roleConverter.convert(roles));
        userService.updateUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
