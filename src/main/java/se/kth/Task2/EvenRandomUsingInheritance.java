package se.kth.Task2;

import java.util.Random;

public class EvenRandomUsingInheritance extends Random{
    private int evenNum;
    @Override
    public int nextInt(int bound){
         evenNum = super.nextInt(bound);
         if (bound < 3)
             return -1;

        while ( evenNum % 2 != 0 || evenNum == 0){
            evenNum = super.nextInt(bound);
        }
        return evenNum;
    }
}
