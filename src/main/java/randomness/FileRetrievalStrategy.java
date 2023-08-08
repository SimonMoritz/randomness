package randomness;

import framework.RetrievalStrategy;

public class FileRetrievalStrategy implements RetrievalStrategy {

    private final String fileName;
    public FileRetrievalStrategy(String fileName){
        this.fileName = fileName;
    }
    @Override
    public int[] getBitsFromExternalRG(int amount) {
        System.out.println(fileName);
        return new int[12];
    }
}
