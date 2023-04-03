package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dmos5.model.User;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance = null;
    private static List<User> database = new ArrayList<User>();
    public UserDaoImpl(){}
    public static UserDaoImpl getInstance(){
        if (instance == null){
            instance = new UserDaoImpl();
        }
        return instance;
    }
    @Override
    public boolean validateUser(String username, String password){
        if (database.isEmpty()){
            return false;
        }else{
            for(User user : database) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void userAdd(User user) {
        if(user != null){
            database.add(user);
        }
    }
    @Override
    public User findByUsername(String username){
        User foundUser = null;
        if (database.isEmpty()){
            return null;
        }else{
            for(User user : database) {
                if (user.getUsername().equals(username)) {
                    foundUser = user;
                }
            }
        }
        return foundUser;
    }
    @Override
    public List<User> findAll() {
        return database;
    }
}
