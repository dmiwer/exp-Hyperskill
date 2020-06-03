package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String s = new Scanner(System.in).nextLine();
        int i = 0;
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", s.charAt(i++), s.charAt(i++), s.charAt(i++));
        System.out.printf("| %c %c %c |%n", s.charAt(i++), s.charAt(i++), s.charAt(i++));
        System.out.printf("| %c %c %c |%n", s.charAt(i++), s.charAt(i++), s.charAt(i++));
        System.out.println("---------");
    }
}
