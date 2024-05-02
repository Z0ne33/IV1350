package se.kth.iv1350.model;

public class Payment {
    private Amount paidAmt;

    private Amount totalCost;
    private Amount change;

    public Payment(Amount paidAmt, Amount totalCost) {
        this.paidAmt = paidAmt;
        this.totalCost = totalCost;

    }
    public Amount getChange(){
        change = new Amount(paidAmt.minus(totalCost).getAmount(), "SEK");
        return change;
    }

    public Amount getTotalCost() {
        return totalCost;
    }

    public Amount getPaidAmt() {
        return paidAmt;
    }


}
