package it.istat.cspro.dashboard.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import it.istat.cspro.dashboard.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    public User findByEmail(String email);

}
