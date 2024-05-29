package se.kth.iv1350.controller;

import se.kth.iv1350.integration.AccountingSystem;
import se.kth.iv1350.integration.InventorySystem;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private Printer printer;
    private InventorySystem inventory;
    private AccountingSystem accounting;


    /**
     * Is Used for Creating a new contr obj
     */
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

    } /**
     * Sends saleDetails
     */

    public SaleDTO saleDetails(){
        return sale.getSaleDetails();

    }
    /**
     *
     * The Function is called when the Sale has ended and updates Inventory and accounting
     */
    public void endSale(){
        inventory.updateInventory(saleDetails());
        accounting.updateAccounting(sale);

    }
    /**
     * The function is called from Main and tells the contr the paid Amount that is sent to sale
     *
     * @param paidAmount is an Amount Obj and takes in the currency and amount that was paid for the sale
     */

    public Amount payment( Amount paidAmount){
        return sale.addPayment(paidAmount);
    }
    /**
     * The main method used to start the entire application.
     *
     * @param itemID is the Item id that is going to be used in order to fetch Item
     * @param quantity represent the quantity of the item that is needed from the customer
     */

    public void fetchItem( String itemID,int quantity){

            sale.addItem(inventory.getItemById(itemID), quantity);

    }

    /**
     * Function takes the reciept that was cconstructed in sale and sends it to the printer
     */
    public void printReciept(){
       Receipt receipt = sale.createReceipt();
       printer.PrintReciept(receipt);

    }

    /**
     * Sends boolean that Checks if item exist in cart mainly used during unit Test
     */
    public boolean checkCart (String ID) {
        return sale.getSaleDetails().getCheckItem(ID);
    }
    /**
     * sets running total
     */
    public void setRunningTotal(){
        sale.setTotal();
    }

}
