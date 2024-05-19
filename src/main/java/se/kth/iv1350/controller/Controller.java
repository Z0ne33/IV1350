package se.kth.iv1350.controller;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.DTO.SaleDTO;
import se.kth.iv1350.model.Receipt;
import se.kth.iv1350.model.Sale;
import se.kth.iv1350.model.SaleObserver;
import se.kth.iv1350.util.ErrorLogger;
import se.kth.iv1350.util.WarningLevel;

import java.io.IOException;
import java.util.logging.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {
    private Sale sale;
    private Printer printer;
    private InventorySystem inventory;
    private AccountingSystem accounting;
    private List<SaleObserver> saleObs;
    ErrorLogger errorLogger;

    DiscountDatabase discountDatabase;

    /**
     * Is Used for Creating a new contr obj
     */
    public Controller(){
        this.inventory = InventorySystem.getInstance();
        this.accounting = AccountingSystem.getInstance();
        this.discountDatabase = DiscountDatabase.getInstance();
        printer = new Printer();
        inventory.createItem();
        saleObs = new ArrayList<>();
        errorLogger = new ErrorLogger();

    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale. 
     */
    public void startSale() {
        sale = new Sale();
        inventory.createItem();
        sale.addSaleObservers(saleObs);
        sale.addObserver(new TotalRevenueFileOutput());

    }
    /**
     *
     *  Function return a Collection of StoreItems that it represent All items That was added to the cart
     *
     */
    public SaleDTO saleDetails(){
        return sale.getSaleDetails();

    }
    /**
     *
     * The Function is called when the Sale has ended and updates Inventory and accounting
     */
    public void endSale() throws InvalidItemException, OperationFailedException {

        try {
            inventory.updateInventory(saleDetails());
            accounting.updateAccounting(sale);
        }
        catch (DatabaseUnavailableException exception){
            errorLogger.log(WarningLevel.WARNING, "Database was unavailable ", exception);
            throw new OperationFailedException("ERROR : Database was Unavailable", exception);

        }


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

    public void fetchItem( String itemID,int quantity) throws OperationFailedException, InvalidItemException {

        try {
                sale.addItem(inventory.getItemById(itemID), quantity);
        }
        catch (DatabaseUnavailableException exception){
            errorLogger.log(WarningLevel.WARNING, "Database was unavailable ", exception);
            throw new OperationFailedException("ERROR: Failed to fetch Item ", exception);
        }


    }

    /**
     * Function takes the reciept that was cconstructed in sale and sends it to the printer
     */
    public void printReciept(){
       Receipt receipt = sale.createReceipt();
       printer.PrintReciept(receipt);
       sale.setSignalDiscount(false);

    }

    /**
     * function signals a discount and fetches discount info from discountDatabase
     *
     * @param costumerID is the id of the customer.
     * @param discountID is the discount that the cashier recived from the customer.
     */
    public void signalDiscount(String costumerID, DiscountIDs discountID){
        sale.setSignalDiscount(true);
        saleDetails().setCurrentCustomer(costumerID);
        sale.discountReduction(discountDatabase.getDiscountByID(saleDetails(), discountID));
        saleDetails().addToCostumers(costumerID);

    }
    public void setRunningTotal(){
        sale.setTotal();
    }


    /**
     * Sends boolean that Checks if item exist in cart mainly used during unit Test
     */
    public boolean checkCart (String ID) {
        return sale.getSaleDetails().checkItem(ID);
    }

    /**
     * The specified observer will be notified when a rental has been paid. There will be
     * notifications only for rentals that are started after this method is called.
     *
     * @param obs The observer to notify.
     */
    public void addSaleObserver( SaleObserver obs ){
        saleObs.add(obs);
    }
}
