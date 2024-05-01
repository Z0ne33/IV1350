package se.kth.iv1350.amazingpos.model;

import java.util.Currency;

public class Amount
{
    private Currency currency;
    private double amount;

    public Amount( double amount, String currency){
        this.amount = amount;
        this.currency = Currency.getInstance(currency);
    }
    public Amount(double amount){
        this.amount = amount;
    }
    public Amount minus(Amount other) {
        return new Amount(amount - other.amount);
    }
    public Amount addition(Amount other){
        return new Amount(amount + other.amount);
    }

    public void increaseAmount( double amount ) {
        this.amount += amount;
    }

    public void setAmount( double amount ) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
