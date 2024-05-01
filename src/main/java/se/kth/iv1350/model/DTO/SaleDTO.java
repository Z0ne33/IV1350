package se.kth.iv1350.amazingpos.model.DTO;

import se.kth.iv1350.amazingpos.model.Amount;
import se.kth.iv1350.amazingpos.model.StoreItem;

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



    public SaleDTO(){
        saleTime = LocalTime.now();
        totalPrice = new Amount(0);
        paidAmount = new Amount(0);
        shoppingCart = new HashMap<>();
        totalVAT = new Amount(0);
        totalPriceNoVAT = new Amount(0);



    }
    public void updatePaidAmount(Amount amount){

    }

    public Amount getTotalPrice() {
        return totalPrice;
    }
    public void setTotal(){

        for (StoreItem item : getAllItems()) {
            totalPrice.increaseAmount((item.getItemDetails().getPrice().getAmount() * (1 + item.getVatRate())) * item.getQuantity() );
            totalPriceNoVAT.increaseAmount(item.getItemDetails().getPrice().getAmount() * item.getQuantity() );
        }
        totalVAT.setAmount(totalPrice.getAmount() - totalPriceNoVAT.getAmount());

    }



    public Amount getTotalVAT() {
        return totalVAT;
    }
    public Map<String, StoreItem> getShoppingCart() {return shoppingCart;}
    public Collection<StoreItem> getAllItems(){

        return shoppingCart.values();
    }
    public StoreItem getShoppingCartItemById(String itemId) {return shoppingCart.get(itemId);}
    public void addToCart( StoreItem item){shoppingCart.put(item.getItemID(), item);}
    public boolean checkItem(String ID){return shoppingCart.containsKey(ID);}
    public void increaseAmount(StoreItem item , int quantity){getShoppingCartItemById(item.getItemID()).setQuantity(item.getQuantity() + quantity);
    }
}
