package se.kth.iv1350.model;

import se.kth.iv1350.model.DTO.ItemDTO;

public class StoreItem {

    private ItemDTO itemDetails;
    private String itemID;
    private int quanity;
    private double vatRate;

    public StoreItem(ItemDTO item, String itemID, int quantity, double VAT){
        this.itemDetails = item;
        this.itemID = itemID;
        this.quanity = quantity;
        this.vatRate = VAT;

    }

    public ItemDTO getItemDetails() {
        return itemDetails;
    }

    public String getItemID(){
        return itemID;
    }
    public int getQuantity(){
        return quanity;
    }
    public void setQuantity(int newQuantity){
        quanity = newQuantity;
    }

    public double getVatRate() {
        return vatRate;
    }

    @Override
    public boolean equals( Object obj ) {
        if(obj == null)
            return false;
        if (!(obj instanceof StoreItem))
            return false;
        return ((StoreItem) obj).itemID == this.itemID;
    }
}