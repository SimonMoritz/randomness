import framework.RetrievalStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import randomness.Generator;
import randomness.FileRetrievalStrategy;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRandomness {
    @BeforeAll
    public static void deleteIndexFile(){
        File f = new File("src/main/java/randomTextFiles/randomStub-index.txt");
        boolean d = f.delete();
        assert (d);
    }
    @Test
    public void shouldBeRandomBitsCorrectLength(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/randomStub");
        Generator g = new Generator(r);
        int[] bits = g.getRandomBits(12);
        assert(bits.length == 12);

    }
    @Test
    public void shouldBeBeFileName(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/randomStub");
        assert(r.fileName.equals( "src/main/java/randomTextFiles/randomStub"));
    }

    @Test
    public void shouldRetrieveBitsFromFile(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/randomStub");
        String bits = r.getSubstringFromFile(1,6);
        System.out.println(bits);
        assert (bits.equals("01001"));
    }

    @Test
    public void shouldBeCorrectLengthOfBitArray(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/randomStub");
        int[] bits;
        try {
            bits = r.getBitsFromExternalRG(23);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
}