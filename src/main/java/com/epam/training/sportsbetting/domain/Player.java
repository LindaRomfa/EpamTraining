package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User{
    private String name;
    private Integer accountNumber;
    private BigDecimal balance;
    private LocalDate birth;
    private Currency currency;

    public Player(String email, String password, String name, Integer accountNumber,
                  BigDecimal balance, LocalDate birth, Currency currency) {
        super(email, password);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.birth = birth;
        this.currency = currency;
    }

    public Player() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String toString(){
        return getEmail() + ", " + getPassword() + ", " + getName() + ", " + getAccountNumber() +
                ", " + getBalance() + ", " + getBirth();
    }
}
