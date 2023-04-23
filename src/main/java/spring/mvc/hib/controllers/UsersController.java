package spring.mvc.hib.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.mvc.hib.dao.UserDao;
import spring.mvc.hib.models.User;

@Controller
public class UsersController {

    private final UserDao userDao;
    @Autowired
    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("usersList", userDao.getUsersList());
        return "users";
    }
    @GetMapping("/users/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDao.getUserByID(id));
        return "user";
    }
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDao.getUserByID(id));
        return "userEdit";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDao.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/users/new")
    public String addUserPage(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/users")
    public String addUser(@ModelAttribute("user") User user) {
        userDao.addUser(user);
        return "redirect:/users";
    }
}
