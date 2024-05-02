package se.kth.iv1350.model;

import java.util.Currency;

public class Amount
{
    private Currency currency;
    private final double amount;

    /**
     * Is used To construct Amount
     *
     * @param amount is double and represent Cash
     * @param currency is the currency that is used
     */

    public Amount( double amount, String currency){
        this.amount = amount;
        this.currency = Currency.getInstance(currency);


    }
    /**
     * Is used To construct Amount
     *
     * @param amount is double and represent Cash
     *
     */

    public Amount(double amount){
        this.amount = amount;
    }

    /**
     * function takes in two amount object and returns a subtracted version of the two
     *
     * @param other is An Amount Object
     * the other Amount object is the Amount obj that called it
     */
    public Amount minus(Amount other) {
        if (this.currency != null){
            return new Amount((amount - other.amount), this.currency.getCurrencyCode());
        }
        return new Amount((amount - other.amount));

    }

    /**
     * function takes in two amount object and returns a version were the 2 object were added togehter
     *
     * @param other is An Amount Object
     * the other Amount object is the Amount obj that called it
     */
    public Amount addition(Amount other){
        if (this.currency != null){
            return new Amount((amount + other.amount), this.currency.getCurrencyCode());
        }
        return new Amount((amount + other.amount));
    }

    /**
     * return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * returns amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * test if 2 Amount object are equal
     * @param obj is the object that is going to test if they are equal
     */
    @Override
    public boolean equals( Object obj ) {

        if (!(obj instanceof Amount)) {
            return false;
        }

        Amount otherAmount = (Amount) obj;
        return this.amount == otherAmount.amount;

    }
}
