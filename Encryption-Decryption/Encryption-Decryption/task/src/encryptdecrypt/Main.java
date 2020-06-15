package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation =scanner.nextLine();
        char[] str = scanner.nextLine().toCharArray();
        int shift  = scanner.nextInt();

        if (operation.equals("enc"))
            encrypt(str, shift);
        else if (operation.equals("dec"))
            decrypt(str, shift);

        System.out.println(new String(str));
    }

    private static void encrypt(char[] str, int shift) {
        for (int i = 0; i < str.length; i++)
            str[i] = (char) ((str[i] + shift) % 256);
    }

    private static void decrypt(char[] str, int shift) {
        for (int i = 0; i < str.length; i++)
            str[i] = (char) ((256 + str[i] - shift) % 256);
    }
}
