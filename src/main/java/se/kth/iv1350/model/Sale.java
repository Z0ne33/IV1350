package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.SaleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {

    private Receipt receipt;
    private SaleDTO saleDetails;
    private Payment pay;
    private Amount discount;
    private Register register;
    List<SaleObserver> saleObs = new ArrayList<>();
    private boolean signalDiscount = false;

    public void setSignalDiscount( boolean signalDiscount ) {
        this.signalDiscount = signalDiscount;
    }

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
            increaseAmount(item, quantity);
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

        this.pay = new Payment(payment, saleDetails.getTotalPrice());
        register.addToRegister(pay);

        notifyObservers();
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
        saleDetails.getShoppingCartItemById(item.getItemID()).setQuantity(item.getQuantity() + quantity);
    }

    /**
     * The specified observer will be notified when this sale has been paid.
     *
     * @param obs The observer to notify.
     */
    public void addObserver(SaleObserver obs) {
        saleObs.add(obs);

    }
    private void notifyObservers(){
        for (SaleObserver obs : saleObs) {
            obs.newSale(getPayment().getTotalCost());
        }
    }
    /**
     * The specified observer will be notified when this sale has been paid.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObservers(List<SaleObserver> obs){
        saleObs.addAll(obs);
    }

    /**
     * sets the total for the sale based on all items that was bought during sale
     */
    public void setTotal(){
        for (StoreItem item : saleDetails.getAllItems()) {

            saleDetails.setTotalPrice(saleDetails.getTotalPrice().addition(new Amount((item.getItemDetails().getPrice().getAmount() * (1 + item.getVatRate())) * item.getQuantity())));
            saleDetails.setTotalPriceNoVAT(saleDetails.getTotalPriceNoVAT().addition(new Amount(item.getItemDetails().getPrice().getAmount() * item.getQuantity())));
        }
        saleDetails.setTotalVAT(saleDetails.getTotalPrice().minus(saleDetails.getTotalPriceNoVAT()));

    }
    /**
     * takes in the sum and reduces it from total cost
     *
     * @param discount is the total sum that will be reduces from total
     */

    public void discountReduction( Amount discount ) {
        setDiscount(discount);
        saleDetails.setTotalPrice(saleDetails.getTotalPrice().minus(discount));
    }
    public boolean getSignalDiscount(){
        return signalDiscount;
    }
    /**
     * sets the discount
     */
    public void setDiscount(Amount discount){
        this.discount = discount;
    }

    /**
     * returns discount
     */
    public Amount getDiscount() {
        return discount;
    }
}
