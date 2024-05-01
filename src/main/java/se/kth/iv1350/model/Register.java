package se.kth.iv1350.amazingpos.model;

public class Register
{
    public Amount registerBalance;
    public Register(){
        registerBalance = new Amount(0);
    }

    public void printRegister() {
        System.out.println(registerBalance);
    }

    public void addToRegister(Payment payment){
        registerBalance = registerBalance.addition(payment.getPaidAmt());

    }


}
