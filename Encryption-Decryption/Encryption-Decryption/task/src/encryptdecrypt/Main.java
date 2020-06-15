package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str = scanner.nextLine().toCharArray();
        int shift  = scanner.nextInt();
        for (int i = 0; i < str.length; i++)
            if (str[i] > 96 && str[i] < 123)
                str[i] = (char) ((str[i] - 97 + shift) % 26 + 97);

        System.out.println(new String(str));
    }
}
