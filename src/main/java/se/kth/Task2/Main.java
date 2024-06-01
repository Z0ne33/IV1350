package se.kth.Task2;



public class Main {
    public static void main(String[] args){



        int evenRandNumInh = new EvenRandomUsingInheritance().nextInt(29);
        int evenRandNumComp = new EvenRandomUsingComposition().nextInt(29);
        System.out.println("Even random that was printed using Inheritance: " + evenRandNumInh);
        System.out.println("Even random that was printed using Composition: " + evenRandNumComp);


    }
}
