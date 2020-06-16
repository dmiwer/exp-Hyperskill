package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String mode = "enc";
        int shift = 0;
        char[] data = new char[0];
        Path in = null;
        Path out = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case ("-mode"):
                    mode = args[++i];
                    break;
                case ("-key"):
                    shift = Integer.parseInt(args[++i]);
                    break;
                case ("-data"):
                    data = args[++i].toCharArray();
                    break;
                case ("-in"):
                    in = Paths.get(args[++i]);
                    break;
                case ("-out"):
                    out = Paths.get(args[++i]);
            }
        }

        if (data.length == 0 && in != null && Files.isRegularFile(in)) {
            try {
                data = Files.readString(in).toCharArray();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        if (mode.equals("enc"))
            encrypt(data, shift);
        else if (mode.equals("dec"))
            decrypt(data, shift);


        if (out != null) {
            try {
                Files.writeString(out, new String(data));
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else
            System.out.println(new String(data));
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
