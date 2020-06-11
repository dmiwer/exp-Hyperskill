package readability;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] sentence = scanner.nextLine().split("[.!?]");
        boolean hard = Stream.of(sentence)
                .map(String::trim)
                .mapToInt(line -> line.split(" ").length)
                .average()
                .getAsDouble() > 10;
        System.out.println(hard ? "HARD" : "EASY");
    }
}