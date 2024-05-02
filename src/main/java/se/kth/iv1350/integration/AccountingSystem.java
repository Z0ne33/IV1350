package se.kth.iv1350.integration;

import se.kth.iv1350.model.Sale;

import java.util.ArrayList;

public class AccountingSystem {
    ArrayList<Sale> sales;
    /**
     * Is used for creating a new Accounting object
     */
    public AccountingSystem(){
        sales = new ArrayList<>();
    }
    /**
     * updates the Arraylist that keeps track of all Sales
     *
     * @param sale is the Sale object that was sent from contr and is added to the Arraylist
     */
    public void updateAccounting(Sale sale){
        sales.add(sale);
    }

    /**
     *  returns sale
     */
    public ArrayList<Sale> showAccounting(){
        return sales;
    }
}
