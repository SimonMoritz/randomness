package randomness;

import framework.Randomness;
import framework.RetrievalStrategy;

import java.io.IOException;

public class Generator implements Randomness {
    private final RetrievalStrategy retrievalStrategy;
    public Generator(RetrievalStrategy retrievalStrategy){
        this.retrievalStrategy = retrievalStrategy;
    }
    @Override
    public int[] getRandomBits(int amount) {
        int[] bits;
        try {
            bits = retrievalStrategy.getBitsFromExternalRG(amount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bits;
    }


    public static void main(String[] args) {
        System.out.println("999999999999999999999999999");
    }
}
