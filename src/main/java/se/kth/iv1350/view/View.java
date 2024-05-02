package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Amount;
import se.kth.iv1350.model.StoreItem;

import static java.lang.System.out;

/**
 * This is a placeholder for the real view. It contains a hardcoded execution with calls to all
 * system operations in the controller.
 */
public class View {
    private final Controller contr;
    
    /**
     * Creates a new instance, that uses the specified controller for all calls to other layers.
     * 
     * @param contr The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void Execution() {


        Amount payment = new Amount(500, "SEK");
        StringBuilder builder = new StringBuilder();
        contr.startSale();


        contr.fetchItem("orange", 3);
        contr.fetchItem("apple", 1);
        contr.fetchItem("BigWheel Oatmeal", 3);
        contr.fetchItem("apple", 1);
        contr.fetchItem("orange", 1);
        contr.fetchItem("BigWheel Oatmeal", 1);

        for (StoreItem item : contr.ShoppingCartItem()) {
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

        contr.endSale();
        out.println("Customer pays "  + payment.getAmount() + "" + payment.getCurrency().getCurrencyCode());
        out.println("Sent sale info to external accounting system.");
        out.println(builder);

        Amount change = contr.payment(payment);

        contr.printReciept();

        out.println("Change to give to the Costumer: " + change.getAmount() + " " + change.getCurrency().getCurrencyCode() );
    }
    private void appendLine(StringBuilder builder, String line) {
        builder.append(line);
        builder.append("\n");
    }


}
