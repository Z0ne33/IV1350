package se.kth.Task2;

import java.util.Random;

public class EvenRandomUsingComposition {
    private Random random = new Random();
    private int evenNum;

    public int nextInt(int bound){
        evenNum = random.nextInt(bound);
        if (bound < 3)
            return -1;

        while ( evenNum % 2 != 0 || evenNum == 0 ){
            evenNum = random.nextInt(bound);
        }
        return evenNum;
    }
}
