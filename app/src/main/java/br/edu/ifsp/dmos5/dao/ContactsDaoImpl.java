package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifsp.dmos5.model.Contact;

public class ContactsDaoImpl implements ContactsDao {

    private final List<Contact> database;

    public ContactsDaoImpl(){
        database = new ArrayList<>(30);

        database.add(new Contact(1,"Fer", "Fernanda", "111"));
        database.add(new Contact(2,"Rafa", "Rafael", "222"));
    }

    @Override
    public void addContacts(Contact contact) {
        if(contact != null) {
            database.add(contact);
        }
    }

    @Override
    public Contact findById(int id) {
        return database.stream()
                .filter(beer1 -> beer1.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Contact> findAll() {
        return database;
    }

    @Override
    public List<Contact> findAll(Order order) {
        Comparator<Contact> comparator = Comparator.comparing(Contact::getPhoneNumber);
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
