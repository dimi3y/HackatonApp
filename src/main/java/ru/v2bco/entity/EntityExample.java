package ru.v2bco.entity;

public class EntityExample {

    private String name;
    private String surname;
    private String account;
    private String balance;

    public EntityExample(String name, String surname, String account, String balance) {
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
