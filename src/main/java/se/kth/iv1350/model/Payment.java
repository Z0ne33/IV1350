package se.kth.iv1350.model;

public class Payment {
    private Amount paidAmt;

    private Amount totalCost;
    private Amount change;

    /**
     * is used to construct Payment class
     */
    public Payment(Amount paidAmt, Amount totalCost) {
        this.paidAmt = paidAmt;
        this.totalCost = totalCost;

    }
    /**
     * creates change by subtraction paidAmount and totalCost and then returns it
     */
    public Amount getChange(){
        change = new Amount(paidAmt.minus(totalCost).getAmount(), "SEK");
        return change;
    }

    /**
     * return total Cost
     */
    public Amount getTotalCost() {
        return totalCost;
    }

    /**
     * return the Amount that was paid
     */
    public Amount getPaidAmt() {
        return paidAmt;
    }


}
