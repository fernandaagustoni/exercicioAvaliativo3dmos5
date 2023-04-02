package br.edu.ifsp.dmos5.dao;

import java.util.List;

import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;

public interface UserDao {
    void userAdd(User user);
    User findByUsername(String username);
    boolean validateUser(String username, String password);
    List<User> findAll();

}