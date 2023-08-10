package randomness;

import framework.Randomness;
import framework.RetrievalStrategy;

public class Generator implements Randomness {
    private final RetrievalStrategy retrievalStrategy;
    public Generator(RetrievalStrategy retrievalStrategy){
        this.retrievalStrategy = retrievalStrategy;
    }
    @Override
    public int[] getRandomBits(int amount) {
        int[] bits;
        bits = retrievalStrategy.getBitsFromExternalRG(amount);
        return bits;
    }

    @Override
    public int[] getRandomNumbersInInterval(int from, int to, int amount) {
        int numberOfBits = (int) Math.ceil((1/Math.log(2))*Math.log(to-from+1));
        int batchNumber = numberOfBits*amount;
        int[] temp;
        temp = getRandomBits(batchNumber);
        int i=0, j=0;
        int[] numbers = new int[amount];

        // loop to calculate random numbers. the variable i is the index for random bits, while j is the index for random numbers calculated with the bits
        while (j<amount){
           if (i<batchNumber){
               int randNumber = 0;
               for (int k=0; k<numberOfBits; k++) {
                   randNumber += temp[i]*((int) Math.pow(2,k));
                   i++;
               }
               if (randNumber < to-from+1){
                   numbers[j] = randNumber + from;
                   j++;
               }
           } else {
               temp = getRandomBits(batchNumber);
               i = 0;
           }
        }
        return numbers;
    }
}
