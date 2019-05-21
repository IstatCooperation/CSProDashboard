package it.istat.cspro.dashboard.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import it.istat.cspro.dashboard.dao.UserDao;
import it.istat.cspro.dashboard.domain.User;
import it.istat.cspro.dashboard.security.CustomUserDetails;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthenticationManager am;

    @Autowired
    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        CustomUserDetails cud;
        if (null == user) {
            notificationService.addErrorMessage("Nessun user presente con  user: " + email);
            throw new UsernameNotFoundException("No user present with user: " + email);
        } else {
            List<String> userRoles = new ArrayList<>();
            userRoles.add(user.getRole());
            cud = new CustomUserDetails(user, userRoles);
            return cud;
        }
    }

    public void authenticate(String name, Object password) {
        Authentication request = new UsernamePasswordAuthenticationToken(name, password);
        Authentication result = am.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);

    }

}
