package randomness;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
            File f = new File(this.fileName + ".txt");
            Scanner sc = new Scanner(f);
            String line = sc.nextLine();
            subString = line.substring(from,to);
        } catch (FileNotFoundException e){
            System.out.println("file not found");
            throw new RuntimeException(e);
        }
        return subString;
    }
    @Override
    public int[] getBitsFromExternalRG(int amount) {
        int from;
        try {
            File f = new File(this.fileName + "-index.txt");
            if (f.createNewFile()){
                from = 0;
                FileWriter fw = new FileWriter(this.fileName + "-index.txt");
                fw.write("0");
                fw.close();
            } else {
                Scanner sc = new Scanner(f);
                String index = sc.nextLine();
                from = Integer.parseInt(index);
                FileWriter fw = new FileWriter(this.fileName + "-index.txt");
                fw.write(Integer.toString(from + amount));
                fw.close();
            }

        } catch (IOException e){
            System.out.println("IO exception");
            throw new RuntimeException(e);
        }
        String stringBits = getSubstringFromFile(from,from + amount);
        int[] bits = new int[stringBits.length()];
        int i = 0;
        for (char c : stringBits.toCharArray()){
            bits[i] = ((int) c) - 48;
            i++;
        }
        return bits;
    }
}
