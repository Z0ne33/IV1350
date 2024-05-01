package se.kth.iv1350.integration;

import se.kth.iv1350.model.Sale;

import java.util.ArrayList;

public class AccountingSystem {
    ArrayList<Sale> sales;
    public AccountingSystem(){
        sales = new ArrayList<>();

    }
    public void updateAccounting(Sale sale){
        sales.add(sale);
    }
}
