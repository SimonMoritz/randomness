import framework.RetrievalStrategy;
import org.junit.jupiter.api.Test;
import randomness.Generator;
import randomness.FileRetrievalStrategy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRandomness {
    @Test
    public void shouldBeRandomBitsCorrectLength(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/2023-08-08.txt");
        Generator g = new Generator(r);
        int[] bits = g.getRandomBits(12);
        System.out.println("amount of bits are");
        System.out.println(bits.length);
        assert(bits.length == 12);
    }
    @Test
    public void shouldBeBeFileName(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/2023-08-08.txt");
        assert(r.fileName.equals( "src/main/java/randomTextFiles/2023-08-08.txt"));
    }

    @Test
    public void shouldRetrieveBitsFromFile(){
        FileRetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/2023-08-08.txt");
        String bits = r.getSubstringFromFile(1,6);
        System.out.println(bits);
        assert (bits.equals("01001"));
    }

    @Test
    public void shouldBeCorrectLengthOfBitArray(){
        RetrievalStrategy r = new FileRetrievalStrategy("src/main/java/randomTextFiles/2023-08-08.txt");
        int[] bits = r.getBitsFromExternalRG(23);
        assert (bits.length == 23);
    }

    @Test
    public void shouldThrowRuntimeErrorOnFileName(){
        RetrievalStrategy r = new FileRetrievalStrategy("doesNotExist.txt");
        //the second argument is a lambda function also an executable, this executable is then checked for runtime error
        assertThrows(RuntimeException.class, () -> r.getBitsFromExternalRG(52));
    }
}