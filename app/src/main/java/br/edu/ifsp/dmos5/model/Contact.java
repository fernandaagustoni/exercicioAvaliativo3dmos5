package br.edu.ifsp.dmos5.model;

public class Contact {
    private String nickname;
    private String name;
    private String phoneNumber;
    public Contact(String nickname, String name, String phoneNumber){
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public String getNickname() {
        return nickname;
    }
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

}
