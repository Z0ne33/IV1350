package se.kth.iv1350.model.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.StoreItem;
import se.kth.iv1350.model.DTO.ItemDTO;

import java.time.LocalTime;
import java.util.*;

public class SaleDTO
{
    private ArrayList<String> costumers;
    private String currentCustomer;
    private Amount totalPrice;
    private Amount totalVAT;
    private Map<String, StoreItem> shoppingCart;


    /**
     * Constructor that is called when an object of SaleDTO is created
     */

    public SaleDTO(Map<String, StoreItem> shoppingCart, Amount totalVAT,Amount totalPrice, ArrayList<String> customers, String currentCustomer){
        this.totalPrice = totalPrice;
        this.shoppingCart = shoppingCart;
        this.totalVAT = totalVAT;
        this.costumers = customers;
        this.currentCustomer = currentCustomer;

    }


    /**
     * return totalPrice
     */
    public Amount getTotalPrice() {
        return totalPrice;
    }

    /**
     * return totalVAT
     */
    public Amount getTotalVAT() {
        return totalVAT;
    }

    /**
     * returns a collection all items Present in the Shopping cart
     */
    public Collection<StoreItem> getAllItems(){return shoppingCart.values();}


    /**
     * Checks if itemID is present in the shoppingCart
     *
     * @param ID is used for searching the Item
     */
    public boolean checkItem(String ID){return shoppingCart.containsKey(ID);}
    /**
     * we get current Customer
     */
    public String getCurrentCustomer() {return currentCustomer;}

    /**
     * we get a list of all Customers
     */
    public ArrayList<String> getCostumers() {return costumers;}
}
