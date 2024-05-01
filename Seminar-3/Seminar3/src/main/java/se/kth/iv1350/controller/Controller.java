package se.kth.iv1350.amazingpos.controller;

import se.kth.iv1350.amazingpos.integration.AccountingSystem;
import se.kth.iv1350.amazingpos.integration.InventorySystem;
import se.kth.iv1350.amazingpos.integration.Printer;
import se.kth.iv1350.amazingpos.model.*;

import java.util.Collection;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private Printer printer;
    private InventorySystem inventory;
    private AccountingSystem accounting;
    public Controller(){
        this.inventory = new InventorySystem();
        this.accounting = new AccountingSystem();
        printer = new Printer();
        inventory.createItem();
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale. 
     */
    public void startSale() {
        sale = new Sale();
        inventory.createItem();

    }
    public Collection<StoreItem> ShoppingCartItem(){
        return sale.getSaleDetails().getShoppingCart().values();

    }
    public void endSale(){
        inventory.updateInventory(ShoppingCartItem());
        accounting.updateAccounting(sale);

    }
    public Amount payment(Amount paidAmount){

        return sale.addPayment(paidAmount);
    }

    public StoreItem fetchItem( String itemID,int quantity){

        if (inventory.checkItem(itemID) || inventory.getItemById(itemID).getQuantity() > quantity){
            sale.addItem(inventory.getItemById(itemID), quantity);
        }
        return inventory.getItemById(itemID);

    }
    public void printReciept(){
       Receipt receipt = sale.createReceipt();
       printer.PrintReciept(receipt);

    }

    

}
