package it.istat.cspro.dashboard.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.istat.cspro.dashboard.dao.UserDao;
import it.istat.cspro.dashboard.domain.User;
import it.istat.cspro.dashboard.forms.UserCreateForm;
import it.istat.cspro.dashboard.forms.UserUpdateForm;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return (List<User>) this.userDao.findAll();
    }

    public User findOne(Long id) {
        return this.userDao.findById(id).orElse(null);
    }

    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    public User create(UserCreateForm uf) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(uf.getEmail());
        user.setPassword(passwordEncoder.encode(uf.getPassword()));
        user.setFirstname(uf.getFirstname());
        user.setLastname(uf.getLastname());
        user.setRole(uf.getRole());
        userDao.save(user);

        return user;
    }

    public User update(UserUpdateForm uf) throws Exception {
        User user = userDao.findById(uf.getId()).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        user.setEmail(uf.getEmail());
        user.setFirstname(uf.getFirstname());
        user.setLastname(uf.getLastname());
        user.setRole(uf.getRole());
        userDao.save(user);

        return user;
    }

    public User updatePasswordByEmail(String email, String password) throws Exception {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
        return user;
    }

    public User updatePasswordById(Long id, String password) throws Exception {
        User user = userDao.findById(id).orElse(null);
        if (user == null) {
            throw new Exception("User not found");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        userDao.save(user);
        return user;
    }

    public void delete(Long id) {
        userDao.deleteById(id);
    }

}
