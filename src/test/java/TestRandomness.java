import framework.RetrievalStrategy;
import org.junit.jupiter.api.Test;
import randomness.Generator;
import randomness.FileRetrievalStrategy;

public class TestRandomness {
    @Test
    public void shouldBeRandom(){
        RetrievalStrategy r = new FileRetrievalStrategy("myFile.txt");
        Generator g = new Generator(r);
        int[] bits = g.getRandomBits(12);
        System.out.println("amount of bits are");
        System.out.println(bits.length);
        assert(bits.length == 12);
        System.out.println("JA");
    }
}