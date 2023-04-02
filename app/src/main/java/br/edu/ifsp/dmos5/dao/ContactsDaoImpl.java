package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.edu.ifsp.dmos5.model.Contact;

public class ContactsDaoImpl implements ContactsDao {
    private static ContactsDaoImpl instance = null;
    private List<Contact> database;
    private ContactsDaoImpl(){};

    public static ContactsDaoImpl getInstance(){
        if (instance == null){
            instance = new ContactsDaoImpl();
        }
        return instance;
    }

    @Override
    public void addContacts(Contact contact) {
        if(contact != null) {
            database.add(contact);
        }
    }

    @Override
    public Contact findByNickname(String username) {
        if (database == null){
            database = new ArrayList<>();
        }
        for (Contact user : database){
            if (Objects.equals(username, user.getNickname())){
                return user;
            }
        }
        return null;

    }
    @Override
    public List<Contact> findAll() {
        return database;
    }

}
