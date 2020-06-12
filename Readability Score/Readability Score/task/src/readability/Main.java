package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of(args[0]);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            System.out.println("Needed a file name");
            return;
        }

        int words = 0, sentences = 0, characters = 0, syllables = 0, polysyllables = 0;
        double score;
        String file = "";
        try {
            file = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String senten : file.split("(?<=[.!?])")) {
            if (senten.matches(".*[\\w]+.*")) {
                sentences++;
                for (String w : senten.split(" ")) {
                    int s = syll(w);
                    if (s > 0) {
                        syllables += s;
                        words++;
                        characters += w.length();
                        if (s > 2) polysyllables++;
                    }
                }
            }
        }

        double ARIscore = 4.71 * characters / words + 0.5 * words / sentences - 21.43;
        double FKscore = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
        double SMOGscore = 1.043 * Math.sqrt((double) polysyllables * 30 / sentences) + 3.1291;
        double CLscore = 5.88 * characters / words - 29.6 * sentences / words - 15.8;

        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String calc = new Scanner(System.in).nextLine();
        System.out.println();
        if (calc.equals("ARI") || calc.equals("all"))
            System.out.printf("Automated Readability Index: %.2f (about %d year olds).%n", .01 * (int)(ARIscore * 100), getAge(ARIscore));
        if (calc.equals("FK") || calc.equals("all"))
            System.out.printf("Flesch–Kincaid readability tests: %.2f (about %d year olds).%n", .01 * (int)(FKscore * 100), getAge(FKscore));
        if (calc.equals("SMOG") || calc.equals("all"))
            System.out.printf("Simple Measure of Gobbledygook: %.2f (about %d year olds).%n", .01 * (int)(SMOGscore * 100), getAge(SMOGscore));
        if (calc.equals("CL") || calc.equals("all"))
            System.out.printf("Coleman–Liau index: %.2f (about %d year olds).%n", .01 * (int)(CLscore * 100), getAge(CLscore));
        System.out.println();
        System.out.printf("This text should be understood in average by %.2f year olds.%n", (getAge(ARIscore) + getAge(FKscore) + getAge(SMOGscore) + getAge(CLscore)) / 4.0);
    }

    private static int syll(String w) {
        w = w.replaceAll("\\W", "").toLowerCase();
        if (w.length() == 0) {
            return 0;
        }
        char[] arr = w.toCharArray();
        int ans = isVowel(arr[0]) ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            if (i == arr.length - 1 && arr[i] == 'e')
                break;
            if (isVowel(arr[i]) && !isVowel(arr[i-1]))
                ans++;
        }
        return  ans > 0 ? ans : 1;
    }

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }

    static int getAge(Double score) {
        switch ((int) Math.round(score)){
            case 1 : return 6;
            case 2 : return 7;
            case 3 : return 9;
            case 4 : return 10;
            case 5 : return 11;
            case 6 : return 12;
            case 7 : return 13;
            case 8 : return 14;
            case 9 : return 15;
            case 10 : return 16;
            case 11 : return 17;
            case 12 : return 18;
            case 13 : return 24;
            default: return 25;
        }
    }
}