package br.edu.ifsp.dmos5.model;

import androidx.annotation.NonNull;
import br.edu.ifsp.dmos5.dao.ContactsDaoImpl;

public class User {
    private String username;
    private String password;
    private ContactsDaoImpl database;
    public User(String username, String password) {
        this.database = new ContactsDaoImpl();
        this.username = username;
        this.password = password;
        this.password = Cryptography.getHashMd5(password);
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    @NonNull
    @Override
    public String toString() {
        return getUsername() + " | " + getPassword();
    }
    public ContactsDaoImpl getContacts(){
        return database;
    }
}
