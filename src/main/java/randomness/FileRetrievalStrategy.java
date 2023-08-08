package randomness;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import framework.RetrievalStrategy;

public class FileRetrievalStrategy implements RetrievalStrategy {

    public final String fileName;
    public FileRetrievalStrategy(String fileName){
        this.fileName = fileName;
    }

    public String getSubstringFromFile(int from, int to){
        String subString;
        try {
            File f = new File(this.fileName);
            Scanner sc = new Scanner(f);
            String line = sc.nextLine();
            subString = line.substring(from,to);
        } catch (FileNotFoundException e){
            System.out.println("file not found");
            throw new RuntimeException();
        }
        return subString;
    }
    @Override
    public int[] getBitsFromExternalRG(int amount) {
        String stringBits = getSubstringFromFile(0,amount);
        int[] bits = new int[stringBits.length()];
        int i = 0;
        for (char c : stringBits.toCharArray()){
            bits[i] = ((int) c) - 48;
            i++;
        }
        return bits;
    }
}
