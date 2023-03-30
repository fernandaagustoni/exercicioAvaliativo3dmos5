package br.edu.ifsp.dmos5.dao;

import java.util.List;

import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;

public interface UserDao {
    int userAdd(User usuario);

    void addContact(User user, Contact contato);
    User findByUsername(String username);
    List<User> findAll();
}
