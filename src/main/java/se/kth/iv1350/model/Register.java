package se.kth.iv1350.model;

public class Register
{
    private Amount registerBalance;

    /**
     * is Used to construct the register
     */
    public Register(){
        registerBalance = new Amount(0);
    }

    /**
     * prints the balance present in register
     */
    public void printRegister() {
        System.out.println(registerBalance);
    }

    /**
     * adds payment to register
     *
     * @param payment is the amount that is added to register
     */
    public void addToRegister(Payment payment){
        registerBalance = registerBalance.addition(payment.getPaidAmt());

    }


}
