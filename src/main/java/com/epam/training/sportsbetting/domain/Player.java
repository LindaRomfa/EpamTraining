package com.epam.training.sportsbetting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User{
    private String name;
    private Integer accountNumber;
    private BigDecimal balance;
    private LocalDate birth;
    private Currency currency;

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

    public Player(String email, String password, String name, Integer accountNumber, BigDecimal balance, LocalDate birth) {
        super(email, password);
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.birth = birth;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String toString(){
        return getEmail() + ", " + getPassword() + ", " + getName() + ", " + getAccountNumber() + ", " + getBalance() + ", " + getBirth();
    }
}
