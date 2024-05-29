package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.DiscountIDs;
import se.kth.iv1350.integration.InvalidItemException;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.StoreItem;
import se.kth.iv1350.util.ErrorLogger;
import se.kth.iv1350.util.WarningLevel;

import java.io.IOException;
import java.util.logging.*;

import static java.lang.System.out;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private final Controller contr;
    private ErrorMessageHandler errorMessageHandler;
    private ErrorLogger errorLogger;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {

        errorMessageHandler = new ErrorMessageHandler();
        errorLogger = new ErrorLogger();
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());



    }
    private void printInfo(StoreItem item, StringBuilder builder){

        out.println("--------------  " + item.getItemDetails().getName().toUpperCase() + "  ----------------");
        out.println("Add "+ item.getQuantity()+" item with item id: " +  item.getItemID());
        out.println("Item ID: " + item.getItemID());
        out.println("Item cost: " + item.getItemDetails().getPrice().getAmount());
        out.println("item description: "+ item.getItemDetails().getDescription() +"\n");

        out.println("VAT: " + item.getVatRate() * 100);
        out.println("Total cost ( incl VAT ):  " + (item.getItemDetails().getPrice().getAmount() * (1 + item.getVatRate())) * item.getQuantity());
        out.println("Total VAT per item:  " + item.getItemDetails().getPrice().getAmount() * item.getVatRate() +"");

        out.println("-------------------------------------------\n \n");

        builder.append("Told external inventory system to decrease inventory quantity ");
        appendLine(builder, "of item " + item.getItemID()  +" by " +item.getQuantity() +" units");
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void Execution() {

        StringBuilder builder = new StringBuilder();


        Amount payment = new Amount(500, "SEK");

        contr.startSale();
        try{
            contr.fetchItem("orange", 3);
            contr.fetchItem("apple", 1);
            contr.fetchItem("BigWheel Oatmeal", 3);
            contr.fetchItem("apple", 1);
            contr.fetchItem("orange", 1);
            contr.fetchItem("BigWheel Oatmeal", 1);

            contr.setRunningTotal();
            contr.signalDiscount("ID", DiscountIDs.FirstTime);

            for (StoreItem item : contr.saleDetails().getAllItems()) {
                printInfo(item, builder);
            }

            contr.endSale();
            out.println("Customer pays "  + payment.getAmount() + "" + payment.getCurrency().getCurrencyCode());
            out.println("Sent sale info to external accounting system.");
            out.println(builder);

            Amount change = contr.payment(payment);

            contr.printReciept();

            out.println("Change to give to the Costumer: " + change.getAmount() + " " + change.getCurrency().getCurrencyCode() );
        }
        catch (InvalidItemException idException){
            errorMessageHandler.showErrorMsg("Invalid item ID has been entered, try enter again");
            errorLogger.log(WarningLevel.WARNING, "Ivalid Item ID", idException);
        }
        catch (OperationFailedException exception){
            errorMessageHandler.showErrorMsg("Operation Failed issue with Database");
            errorLogger.log(WarningLevel.WARNING, "Operation Failed, ", exception);
        }



    }
    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }


}
