package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int base = scanner.nextInt();
        String s;
        switch (base){
            case (2):
                s = "0b" + Integer.toBinaryString(num);
                break;
            case (8):
                s = "0" + Integer.toOctalString(num);
                break;
            case (16):
                s = "0x" + Integer.toHexString(num);
                break;
            default:
                s = "xxx";
        }
        System.out.println(s);
    }
}
