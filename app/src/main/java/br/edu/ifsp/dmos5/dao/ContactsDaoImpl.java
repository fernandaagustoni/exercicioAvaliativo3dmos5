package br.edu.ifsp.dmos5.dao;

import java.util.ArrayList;
import java.util.List;
import br.edu.ifsp.dmos5.model.Contact;

public class ContactsDaoImpl implements ContactsDao {
    private List<Contact> database = new ArrayList<Contact>();
    public ContactsDaoImpl(){}
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
            for(Contact contacts : database) {
                if(contacts != null) {
                    if (contacts.getNickname().equals(nickname)) {
                        foundContact = contacts;
                    }
                }
            }
        }
        return foundContact;
    }
    @Override
    public List<Contact> findAll() {
        return database;
    }
}
