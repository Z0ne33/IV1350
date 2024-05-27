package se.kth.iv1350.model.DTO;

import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.StoreItem;
import se.kth.iv1350.model.DTO.ItemDTO;

import java.time.LocalTime;
import java.util.*;

public class SaleDTO
{
    private Amount paidAmount;
    private Amount totalPrice;
    private Amount totalPriceNoVAT;
    private Amount totalVAT;
    private LocalTime saleTime;
    private Map<String, StoreItem> shoppingCart;

    /**
     * Cunstructor for SaleDTO
     *
     */

    public SaleDTO(){
        saleTime = LocalTime.now();
        totalPrice = new Amount(0, "SEK");
        paidAmount = new Amount(0);
        shoppingCart = new HashMap<>();
        totalVAT = new Amount(0);
        totalPriceNoVAT = new Amount(0, "SEK");

    }


    /**
     * we set the TotalVAT
     */
    public void setTotalVAT( Amount totalVAT ) {
        this.totalVAT = totalVAT;
    }
    /**
     * we set the TotalpriceNoVAT
     */
    public void setTotalPriceNoVAT( Amount totalPriceNoVAT ) {
        this.totalPriceNoVAT = totalPriceNoVAT;
    }

    /**
     * we set the totalPrice
     */
    public void setTotalPrice( Amount totalPrice ) {
        this.totalPrice = totalPrice;
    }

    /**
     * we get TotalPrice
     */
    public Amount getTotalPriceNoVAT() {
        return totalPriceNoVAT;
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
     * retrieves item present in the shopping Cart by using itemID
     *
     * @param itemId is used for searching the item
     */
    public StoreItem getShoppingCartItemById(String itemId) {return shoppingCart.get(itemId);}

    /**
     * function adds Item to the Shopping Cart
     *
     * @param item is the item that will be added into The ShoppingCart
     */
    public void addToCart( StoreItem item){shoppingCart.put(item.getItemID(), item);}

    /**
     * Checks if itemID is present in the shoppingCart
     *
     * @param ID is used for searching the Item
     */
    public boolean checkItem(String ID){return shoppingCart.containsKey(ID);}

}
