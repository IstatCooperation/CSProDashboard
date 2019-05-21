package it.istat.cspro.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.istat.cspro.dashboard.bean.Role;
import it.istat.cspro.dashboard.domain.User;
import it.istat.cspro.dashboard.forms.LoginForm;
import it.istat.cspro.dashboard.forms.UserCreateForm;
import it.istat.cspro.dashboard.forms.UserUpdateForm;
import it.istat.cspro.dashboard.service.NotificationService;
import it.istat.cspro.dashboard.service.UserService;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@Controller
public class UserController extends BaseController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String showLoginForm(LoginForm loginForm) {
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        notificationService.addInfoMessage("Logout Ok !");
        return "redirect:/";
    }

    @RequestMapping(value = "/users/newuser", method = RequestMethod.GET)
    public String getUserCreatePage(Model model, @ModelAttribute("userCreateForm") UserCreateForm form) {
        notificationService.removeAllMessages();
        Role[] allRoles = Role.values();
        model.addAttribute("allRoles", allRoles);
        return "users/newuser";
    }

    @RequestMapping(value = "/users/edituser", method = RequestMethod.GET)
    public String getEditUser(Model model, Principal principal) {
        notificationService.removeAllMessages();

        User user = userService.findByEmail(principal.getName());
        UserCreateForm userf = new UserCreateForm(user);
        Role[] allRoles = Role.values();

        model.addAttribute("userCreateForm", userf);
        model.addAttribute("allRoles", allRoles);

        return "users/edituser";
    }

    @RequestMapping(value = "/users/edituser", method = RequestMethod.POST)
    public String editUser(Model model, @Valid @ModelAttribute("userCreateForm") UserUpdateForm form,
            BindingResult bindingResult) {

        notificationService.removeAllMessages();
        Role[] allRoles = Role.values();
        model.addAttribute("allRoles", allRoles);

        if (bindingResult.hasErrors()) {
            return "users/edituser";
        }

        try {
            userService.update(form);
            notificationService.addInfoMessage("User updated");
        } catch (Exception e) {
            notificationService.addErrorMessage("Error: " + e.getMessage());
            return "users/edituser";
        }

        return "users/edituser";
    }

    @RequestMapping(value = "/users/newuser", method = RequestMethod.POST)
    public String handleUserCreateForm(Model model, @Valid @ModelAttribute("userCreateForm") UserCreateForm form,
            BindingResult bindingResult) {
        notificationService.removeAllMessages();
        Role[] allRoles = Role.values();
        model.addAttribute("allRoles", allRoles);

        if (bindingResult.hasErrors()) {
            return "users/newuser";
        }

        try {
            userService.create(form);
            notificationService.addInfoMessage("User created");
        } catch (Exception e) {
            notificationService.addErrorMessage("Error: " + e.getMessage());
            return "users/newuser";
        }

        return "users/newuser";
    }

    @RequestMapping(value = "/users/userlist")
    public String userslist(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        Role[] allRoles = Role.values();
        model.addAttribute("allRoles", allRoles);
        return "users/userlist";
    }

}
