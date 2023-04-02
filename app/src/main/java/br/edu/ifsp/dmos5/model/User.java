package br.edu.ifsp.dmos5.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;

public class User {
    private String username;
    private String password;
    private ContactsDaoImpl database;
    public User(String username, String password) {
        this.database = new ContactsDaoImpl();
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @NonNull
    @Override
    public String toString() {
        return getUsername() + " | " + getPassword();
    }
    public void addContact(ContactsDaoImpl database) {
        this.database = database;
    }
    public ContactsDaoImpl getContacts(){
        return database;
    }
}
