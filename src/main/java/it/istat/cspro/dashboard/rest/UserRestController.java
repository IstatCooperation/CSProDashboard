package it.istat.cspro.dashboard.rest;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.istat.cspro.dashboard.domain.User;
import it.istat.cspro.dashboard.forms.UserCreateForm;
import it.istat.cspro.dashboard.forms.UserUpdateForm;
import it.istat.cspro.dashboard.service.NotificationService;
import it.istat.cspro.dashboard.service.UserService;
import it.istat.cspro.dashboard.service.NotificationService.NotificationMessage;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users/restlist")
    public List<User> userslist(Model model) {
        return userService.findAll();
    }

    @RequestMapping(value = "/users/restgetUser")
    public User getUser(@RequestParam("id") Long id) {
        return userService.findOne(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users/restNewUser", method = RequestMethod.POST)
    public List<NotificationMessage> newUser(@Valid @ModelAttribute("userCreateForm") UserCreateForm form, BindingResult bindingResult) {
        notificationService.removeAllMessages();
        if (!bindingResult.hasErrors()) {
            try {
                userService.create(form);
                notificationService.addInfoMessage("User created");
            } catch (Exception e) {
                notificationService.addErrorMessage("Error: " + e.getMessage());
            }
        } else {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                notificationService.addErrorMessage(error.getField() + " - " + error.getDefaultMessage());
            }
        }
        return notificationService.getNotificationMessages();
    }

    @RequestMapping(value = "/users/restUpdateUser", method = RequestMethod.POST)
    public List<NotificationMessage> updateUser(@Valid @ModelAttribute("userCreateForm") UserUpdateForm form, BindingResult bindingResult) {
        notificationService.removeAllMessages();
        if (!bindingResult.hasErrors()) {
            try {
                userService.update(form);
                notificationService.addInfoMessage("User updated");
            } catch (Exception e) {
                notificationService.addErrorMessage("Error: " + e.getMessage());
            }
        } else {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                notificationService.addErrorMessage(error.getField() + " - " + error.getDefaultMessage());
            }
        }
        return notificationService.getNotificationMessages();
    }

    @RequestMapping(value = "/users/restDeleteUser", method = RequestMethod.POST)
    public List<NotificationMessage> deleteUser(@RequestParam("id") Long id) {
        // contents as before
        notificationService.removeAllMessages();
        //get logged in user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id.compareTo(user.getId()) == 0) {
            notificationService.addErrorMessage("Error: User can not be deleted !");
        } else {
            try {
                userService.delete(id);
                notificationService.addInfoMessage("User deleted");
            } catch (Exception e) {
                notificationService.addErrorMessage("Error: " + e.getMessage());
            }
        }
        return notificationService.getNotificationMessages();
    }

    @RequestMapping(value = "/users/updateMyPassword", method = RequestMethod.POST)
    public List<NotificationMessage> updateMyPassword(@RequestParam("passw") String password, Principal principal) {
        String email = principal.getName();
        notificationService.removeAllMessages();
        try {
            userService.updatePasswordByEmail(email, password);
            notificationService.addInfoMessage("User updated");
        } catch (Exception e) {
            notificationService.addErrorMessage("Error: " + e.getMessage());
        }
        return notificationService.getNotificationMessages();
    }

    @RequestMapping(value = "/users/updatePassword", method = RequestMethod.POST)
    public List<NotificationMessage> updatePassword(@RequestParam("passw") String password, @RequestParam("id") String id) {
        notificationService.removeAllMessages();
        try {
            userService.updatePasswordById(Long.parseLong(id), password);
            notificationService.addInfoMessage("Password updated");
        } catch (Exception e) {
            notificationService.addErrorMessage("Error: " + e.getMessage());
        }
        return notificationService.getNotificationMessages();
    }

}
