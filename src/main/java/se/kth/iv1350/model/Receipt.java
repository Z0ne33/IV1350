package se.kth.iv1350.model;

import java.time.LocalDateTime;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt {


     Sale saleDetails;

    /**
     *  Constructs the sale Obj
     *
     * @param saleDetails represent the sale Obj
     */
   public Receipt(Sale saleDetails) {
         this.saleDetails = saleDetails;

   }

    /**
     * This is where the reciept is constructed meaning this is where it is created
     */
    public String createReceiptString() {
        StringBuilder builder = new StringBuilder();
        appendLine(builder, "----------- BEGIN RECEIPT --------------");
        endSection(builder);
        LocalDateTime saleTime = LocalDateTime.now();
        builder.append("Sale time: ");
        appendLine(builder, saleTime.toString());
        endSection(builder);
        for ( StoreItem item: saleDetails.getSaleDetails().getAllItems()) {
            builder.append(item.getItemDetails().getName());
            builder.append(" \t" + item.getQuantity()  +" x " + item.getItemDetails().getPrice().getAmount());
            appendLine(builder, " \t" + item.getQuantity() * item.getItemDetails().getPrice().getAmount() + " " + item.getItemDetails().getPrice().getCurrency().getCurrencyCode());
        }

        builder.append(" \nTotal: ");
        appendLine(builder, Double.toString(saleDetails.getPayment().getTotalCost().getAmount()));
        builder.append("Total VAT: ");
        appendLine(builder, Double.toString(saleDetails.getSaleDetails().getTotalVAT().getAmount()));

        builder.append("\nCash: ");
        appendLine(builder, Double.toString(saleDetails.getPayment().getPaidAmt().getAmount()));
        builder.append("Change: ");
        appendLine(builder, Double.toString(saleDetails.getPayment().getChange().getAmount()));
        endSection(builder);
        appendLine(builder, "----------- END RECEIPT --------------");
        return builder.toString();

    }
    private void appendLine(StringBuilder builder, String line) {
         builder.append(line);
         builder.append("\n");
    }
    private void endSection(StringBuilder builder) {
        builder.append("\n");
    }




}
