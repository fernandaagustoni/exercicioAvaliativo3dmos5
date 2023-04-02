package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.edu.ifsp.dmos5.model.Contact;
import br.edu.ifsp.dmos5.model.User;

public class ContactsDaoImpl implements ContactsDao {
    private static ContactsDaoImpl instance = null;
    private List<Contact> database = new ArrayList<Contact>();
    public ContactsDaoImpl(){}
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
    public Contact findByNickname(String nickname) {
        Contact foundContact = null;
        if (database.isEmpty()){
            return null;
        }else{
            for(Contact user : database) {
                if (user.getNickname().equals(nickname)) {
                    foundContact = user;
                }
            }
        }
        return foundContact;
    }
    @Override
    public List<Contact> findAll() {
        return database;
    }

    @Override
    public List<Contact> findAll(Order order) {
        Comparator<Contact> comparator = Comparator.comparing(Contact::getNickname);
        if(order == Order.ALPHABETICALLY) {
            return database.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }else{
            return database.stream()
                    .sorted(comparator.reversed())
                    .collect(Collectors.toList());
        }
    }

}
