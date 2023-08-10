package framework;

public interface Randomness {
    int[] getRandomBits(int amount);

    int[] getRandomNumbersInInterval(int from, int to, int amount);

}
