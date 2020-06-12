package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of(args[0]);
        if (Files.notExists(path) || Files.isDirectory(path)) {
            System.out.println("Needed a file name");
            return;
        }

        int words = 0, sentences = 0, characters = 0;
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
                    if (w.length() > 0) {
                        words++;
                        characters += w.length();
                    }
                }
            }
        }

        score = 4.71 * characters / words + 0.5 * words / sentences - 21.43;

        System.out.println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.printf("The score is: %.2f%n", .01 * (int)(score * 100));
        System.out.printf("This text should be understood bo %s year olds.%n", getAge(score));
    }

    static String getAge(Double score) {
        switch ((int) Math.ceil(score)){
            case 1 : return "5-6";
            case 2 : return "6-7";
            case 3 : return "7-9";
            case 4 : return "9-10";
            case 5 : return "10-11";
            case 6 : return "11-12";
            case 7 : return "12-13";
            case 8 : return "13-14";
            case 9 : return "14-15";
            case 10 : return "15-16";
            case 11 : return "16-17";
            case 12 : return "17-18";
            case 13 : return "18-24";
            default: return "24+";
        }
    }
}