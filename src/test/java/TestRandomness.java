import org.junit.jupiter.api.Test;
import randomness.Generator;

public class TestRandomness {
    @Test
    public void shouldBeRandom(){

        Generator g = new Generator();
        int[] bits = g.getRandomBits(12);
        System.out.println("amount of bits are");
        System.out.println(bits.length);
        assert(bits.length == 12);
        System.out.println("FUCK JA");
    }
}