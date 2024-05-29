package se.kth.iv1350.model.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.StoreItem;

import java.time.LocalTime;
import java.util.*;

public class SaleDTO
{
    private Amount totalPrice;
    private Amount totalVAT;
    private Map<String, StoreItem> shoppingCart;

    /**
     * Cunstructor for SaleDTO
     *
     */

    public SaleDTO(Map<String, StoreItem> shoppingCart, Amount totalVAT,Amount totalPrice){
        this.totalPrice = totalPrice;
        this.shoppingCart = shoppingCart;
        this.totalVAT = totalVAT;


    }
    /**
     * return totalPrice
     */
    public Amount getTotalPrice() {return totalPrice;}

    /**
     * return totalVAT
     */
    public Amount getTotalVAT() {return totalVAT;}


    /**
     * returns a collection all items Present in the Shopping cart
     */
    public Collection<StoreItem> getAllItems()
    {
        return Collections.unmodifiableCollection(shoppingCart.values());
    }


    /**
     * Checks if itemID is present in the shoppingCart
     *
     * @param ID is used for searching the Item
     */
    public boolean getCheckItem( String ID){return shoppingCart.containsKey(ID);}

}
