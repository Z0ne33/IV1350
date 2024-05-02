package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.SaleDTO;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {

    private Receipt receipt;
    private SaleDTO saleDetails;
    private Payment pay;
    private Register register;
    
    /**
     * Creates a new instance and saves the time of the sale.
     */

    public Sale() {
       saleDetails = new SaleDTO();
       register = new Register();
    }
    /**
     * function adds and item the shopping cart if it already is present increase amount
     *
     * @param item is the item that is being added
     * @param quantity is the quantity of items that you want to add
     */
    public void addItem( StoreItem item, int quantity ){
        if (saleDetails.checkItem(item.getItemID())){
            saleDetails.increaseAmount(item, quantity);
        }
        else{
            item.setQuantity(quantity);
            saleDetails.addToCart(item);
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
        saleDetails.setTotal();
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


}
