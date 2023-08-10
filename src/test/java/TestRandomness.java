import framework.Randomness;
import framework.RetrievalStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import randomness.APIRetrievalStrategy;
import randomness.Generator;
import randomness.FileRetrievalStrategy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRandomness {
    @BeforeAll
    public static void deleteIndexFile(){
        try {
            FileWriter fw = new FileWriter("src/test/java/stubs/randomStub-index.txt");
            fw.write("0");
            fw.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @Test
    public void shouldBeRandomBitsCorrectLength(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/test/java/stubs/randomStub");
        Generator g = new Generator(r);
        int[] bits = g.getRandomBits(12);
        assert(bits.length == 12);

    }
    @Test
    public void shouldBeBeFileName(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/test/java/stubs/randomStub");
        assert(r.fileName.equals( "src/test/java/stubs/randomStub"));
    }

    @Test
    public void shouldRetrieveBitsFromFile(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/test/java/stubs/randomStub");
        String bits = r.getSubstringFromFile(1,6);
        assert (bits.equals("01001"));
    }

    @Test
    public void shouldBeCorrectLengthOfBitArray(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/test/java/stubs/randomStub");
        int[] bits;
        bits = r.getBitsFromExternalRG(23);
        assert (bits.length == 23);
    }

    @Test
    public void shouldThrowRuntimeErrorOnFileName(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/doesNotExist");
        //the second argument is a lambda function also an executable, this executable is then checked for runtime error
        assertThrows(RuntimeException.class, () -> r.getBitsFromExternalRG(52));

        try { //delete new index file
            File f = new File("src/main/java/randomTextFiles/doesNotExist-index.txt");
            boolean d = f.delete();
            assert (d);
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldFailToWriteToFile(){
        //the file src/test/java/stubs/randomStubShouldHaveNoWritePermission-index.txt needs to have no write permission
        //otherwise ignore test
        RetrievalStrategy r = new FileRetrievalStrategy("src/test/java/stubs/randomStubShouldHaveNoWritePermission");
        //the second argument is a lambda function also an executable, this executable is then checked for runtime error
        RuntimeException e = assertThrows(RuntimeException.class, () -> r.getBitsFromExternalRG(52));
        assert (e.getMessage().equals("can't write to file " + "src/test/java/stubs/randomStubShouldHaveNoWritePermission" + "-index.txt"));
    }

    @Test
    public void shouldGiveRandomNumbersInRange(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/2023-08-08");
        Generator g = new Generator(r);
        int[] numbers = g.getRandomNumbersInInterval(6, 10, 20);
        assert (numbers.length == 20);
        for (int n : numbers){
            assert (n <= 10);
            assert (6 <= n);
        }
    }

    @Test
    public void shouldUseAPICorrectly(){
        APIRetrievalStrategy r = new APIRetrievalStrategy();
        int[] bits = r.getBitsFromExternalRG(20);
        assert (bits.length == 20);
        for (int b : bits){
            assert (b == 0 || b == 1);
        }
    }

    @Test
    public void shouldUseAPIStrategyCorrectly(){
        RetrievalStrategy r = new APIRetrievalStrategy();
        Randomness g = new Generator(r);
        int[] bits = g.getRandomBits(6);
        assert (bits.length == 6);
        for (int b : bits){
            assert (b == 0 || b == 1);
        }
    }

    @Test
    public void shouldFailWithException(){
        RetrievalStrategy r = new APIRetrievalStrategy();
        Randomness g = new Generator(r);
        assertThrows(Exception.class, () -> g.getRandomBits(-5));
    }
}