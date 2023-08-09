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
}
