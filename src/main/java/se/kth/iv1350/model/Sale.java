package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.SaleDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {

    private Receipt receipt;
    private SaleDTO saleDetails;
    private Payment pay;
    private Register register;
    private Amount totalPrice;
    private Amount totalVAT;
    private Amount paidAmount;
    private Map<String, StoreItem> shoppingCart;


    /**
     * Creates a new instance and saves the time of the sale.
     */

    public Sale() {
        totalPrice = new Amount(0);
        totalVAT = new Amount(0);
        paidAmount = new Amount(0);
        shoppingCart = new HashMap<>();
       register = new Register();
    }
    /**
     * function adds and item the shopping cart if it already is present increase amount
     *
     * @param item is the item that is being added
     * @param quantity is the quantity of items that you want to add
     */
    public void addItem( StoreItem item, int quantity ){
        if (shoppingCart.containsKey(item.getItemID())){
            increaseAmount(item, quantity);
        }
        else{
            item.setQuantity(quantity);
            addToCart(item);
        }


    }

    /**
     * return SaleDTO object that holds saleDetails
     */
    public SaleDTO getSaleDetails(){
        return saleDetails;
    }
    /**
     * function sets total and pays adds payment to register and returns the change
     *
     * @param payment is the amount that was paid
     */
    public Amount addPayment(Amount payment){
        this.pay = new Payment(payment, saleDetails.getTotalPrice());
        register.addToRegister(pay);
        return pay.getChange();

    }
    /**
     * returns the pay object that represents the amount that was paid
     */
    public Payment getPayment(){
        return pay;
    }

    /**
     * constructs receipt and after that returns it
     */
    public Receipt createReceipt(){
        receipt = new Receipt(this);
        return receipt;
    }
    /**
     * Function takes in item searches for it and increases its quantity
     *
     * @param item is the item that is taken in
     * @param quantity is the quantity that is taken in
     */

    public void increaseAmount(StoreItem item , int quantity){
        shoppingCart.get(item.getItemID()).setQuantity(item.getQuantity() + quantity);
    }
    /**
     * sets the total for the sale based on all items that was bought during sale
     */
    public void setTotal(){
        Amount totalPriceNoVAT = new Amount(0);
        for (StoreItem item : shoppingCart.values()) {

            totalPrice = totalPrice.addition(new Amount((item.getItemDetails().getPrice().getAmount() * (1 + item.getVatRate())) * item.getQuantity()));
            totalPriceNoVAT = totalPriceNoVAT.addition(new Amount(item.getItemDetails().getPrice().getAmount() * item.getQuantity()));
        }
        totalVAT = totalPrice.minus(totalPriceNoVAT);
        saleDetails = new SaleDTO(shoppingCart, totalVAT, totalPrice);

    }

    /**
     * function adds Item to the Shopping Cart
     *
     * @param item is the item that will be added into The ShoppingCart
     */
    public void addToCart( StoreItem item){shoppingCart.put(item.getItemID(), item);}


}
