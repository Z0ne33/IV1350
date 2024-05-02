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



    public SaleDTO(){
        saleTime = LocalTime.now();
        totalPrice = new Amount(0, "SEK");
        paidAmount = new Amount(0);
        shoppingCart = new HashMap<>();
        totalVAT = new Amount(0);
        totalPriceNoVAT = new Amount(0, "SEK");

    }
    public void setTotal(){

        for (StoreItem item : getAllItems()) {
            totalPrice = totalPrice.addition(new Amount((item.getItemDetails().getPrice().getAmount() * (1 + item.getVatRate())) * item.getQuantity()) );
            totalPriceNoVAT = totalPriceNoVAT.addition(new Amount(item.getItemDetails().getPrice().getAmount() * item.getQuantity()) );
        }
        totalVAT = totalPrice.minus(totalPriceNoVAT);

    }
    public Amount getTotalPrice() {
        return totalPrice;
    }
    public Amount getTotalVAT() {
        return totalVAT;
    }
    public Map<String, StoreItem> getShoppingCart() {return shoppingCart;}
    public Collection<StoreItem> getAllItems(){return shoppingCart.values();}
    public StoreItem getShoppingCartItemById(String itemId) {return shoppingCart.get(itemId);}
    public void addToCart( StoreItem item){shoppingCart.put(item.getItemID(), item);}
    public boolean checkItem(String ID){return shoppingCart.containsKey(ID);}
    public void increaseAmount(StoreItem item , int quantity){getShoppingCartItemById(item.getItemID()).setQuantity(item.getQuantity() + quantity);
    }
}
