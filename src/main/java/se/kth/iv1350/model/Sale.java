package se.kth.iv1350.amazingpos.model;

import se.kth.iv1350.amazingpos.model.DTO.SaleDTO;

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
    public void addItem( StoreItem item, int quantity ){
        if (saleDetails.checkItem(item.getItemID())){
            saleDetails.increaseAmount(item, quantity);
        }
        else{
            item.setQuantity(quantity);
            saleDetails.addToCart(item);
        }

    }

    public SaleDTO getSaleDetails(){
        return saleDetails;
    }
    public Amount addPayment(Amount payment){
        saleDetails.setTotal();
        this.pay = new Payment(payment, saleDetails.getTotalPrice());
        register.addToRegister(pay);
        return pay.getChange();


    }
    public Payment getPayment(){
        return pay;
    }
    public Receipt createReceipt(){
        receipt = new Receipt(this);
        return receipt;
    }


}
