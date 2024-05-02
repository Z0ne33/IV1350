package se.kth.iv1350.model;

import java.util.Currency;

public class Amount
{
    private Currency currency;
    private final double amount;

    public Amount( double amount, String currency){
        this.amount = amount;
        this.currency = Currency.getInstance(currency);


    }

    public Amount(double amount){
        this.amount = amount;
    }
    public Amount minus(Amount other) {
        if (this.currency != null){
            return new Amount((amount - other.amount), this.currency.getCurrencyCode());
        }
        return new Amount((amount - other.amount));

    }
    public Amount addition(Amount other){
        if (this.currency != null){
            return new Amount((amount + other.amount), this.currency.getCurrencyCode());
        }
        return new Amount((amount + other.amount));
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals( Object obj ) {

        if (!(obj instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) obj;
        return this.amount == otherAmount.amount;

    }
}
