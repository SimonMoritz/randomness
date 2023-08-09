package randomness;

import framework.RetrievalStrategy;

public class APIRetrievalStrategy implements RetrievalStrategy {

    @Override
    public int[] getBitsFromExternalRG(int amount) {
        return new int[0];
    }
}
