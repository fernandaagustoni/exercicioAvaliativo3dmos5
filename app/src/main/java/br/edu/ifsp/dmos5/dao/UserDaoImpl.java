package br.edu.ifsp.dmos5.dao;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance = null;
    private List<User> database;
    private UserDaoImpl(){};

    public static UserDaoImpl getInstance(){
        if (instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public int userAdd(User users) {
        if (database == null){
            database = new ArrayList<>();
        }
        if(users != null){
            for (User user : database) {
                if (Objects.equals(users.getUsername(), user.getUsername())) {
                    return 0;
                }
            }

            database.add(users);
            int i = 0;
            Log.d("myTag", "usuario" + database.get(i).getUsername());
            i++;
            return 1;
        }
        return 0;
    }

    @Override
    public void addContact(User user, Contact contact) {
        for (User usuario: database){
            if (usuario == user){
                user.addContact(contact);
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        if (database == null){
            database = new ArrayList<>();
        }
        for (User user : database){
            if (username == user.getUsername()){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return database;
    }
}
