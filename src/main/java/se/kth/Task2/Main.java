package se.kth.Task2;



public class Main {
    public static void main(String[] args){



        int evenRandNumInh = new EvenRandomUsingInheritance().nextInt(29);
        int evenRandNumComp = new EvenRandomUsingComposition().nextInt(29);
        System.out.println(evenRandNumInh);
        System.out.println(evenRandNumComp);


    }
}