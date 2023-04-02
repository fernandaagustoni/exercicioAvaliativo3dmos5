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
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getName() {
        return name;
    }
    public void setName(String nome) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
