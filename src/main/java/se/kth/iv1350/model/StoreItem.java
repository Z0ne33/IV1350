package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.ItemDTO;

public class StoreItem {

    private ItemDTO itemDetails;
    private String itemID;
    private int quanity;
    private double vatRate;

    /**
     * Is used to construct the Store Item object
     */

    public StoreItem(ItemDTO item, String itemID, int quantity, double VAT){
        this.itemDetails = item;
        this.itemID = itemID;
        this.quanity = quantity;
        this.vatRate = VAT;

    }

    /**
     * return the itemDetails meaning itemDTO
     */
    public ItemDTO getItemDetails() {
        return itemDetails;
    }

    /**
     * return the item ID
     */

    public String getItemID(){
        return itemID;
    }
    /**
     * returns the quantity
     */
    public int getQuantity(){
        return quanity;
    }
    /**
     * is used to set the quantity
     */
    public void setQuantity(int newQuantity){
        quanity = newQuantity;
    }

    /**
     * function returns the vat Rate
     */
    public double getVatRate() {
        return vatRate;
    }
    /**
     * function tells you if two object are equal or not
     *
     * @param obj is the object that is being tested if it is equal or not.
     */
    @Override
    public boolean equals( Object obj ) {
        if(obj == null)
            return false;
        if (!(obj instanceof StoreItem))
            return false;
        return ((StoreItem) obj).itemID == this.itemID;
    }
}