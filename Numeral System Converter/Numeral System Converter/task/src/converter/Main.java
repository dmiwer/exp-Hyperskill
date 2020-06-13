package converter;

public class Main {
    public static void main(String[] args) {
        int t = 10;
        System.out.printf("%d = %s%n", t, "0b" + Integer.toBinaryString(t));
    }
}
