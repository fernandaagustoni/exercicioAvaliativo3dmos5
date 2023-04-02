package br.edu.ifsp.dmos5.dao;

import java.util.List;

import br.edu.ifsp.dmos5.model.Contact;

public interface ContactsDao {
    void addContacts(Contact contact);
    Contact findByNickname(String username);
    List<Contact> findAll();
    List<Contact> findAll(Order order);
}