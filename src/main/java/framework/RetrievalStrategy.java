package framework;

import java.io.IOException;

public interface RetrievalStrategy {
    int[] getBitsFromExternalRG(int amount) throws IOException;
}
