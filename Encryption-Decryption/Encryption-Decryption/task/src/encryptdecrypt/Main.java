package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

interface Strategy {
    void encrypt(char[] str, int shift);

    void decrypt(char[] str, int shift);
}

public class Main {
    public static void main(String[] args) {
        Strategy strategy = null;
        String mode = "enc";
        int shift = 0;
        char[] data = new char[0];
        Path in = null;
        Path out = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case ("-alg"):
                    if (args[++i].equals("unicode"))
                        strategy = new UnicodeStrategy();
                    break;
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

        if (strategy == null)
            strategy = new ShiftStrategy();

        if (data.length == 0 && in != null && Files.isRegularFile(in)) {
            try {
                data = Files.readString(in).toCharArray();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
        if (mode.equals("enc"))
            strategy.encrypt(data, shift);
        else if (mode.equals("dec"))
            strategy.decrypt(data, shift);


        if (out != null) {
            try {
                Files.writeString(out, new String(data));
            } catch (IOException e) {
                System.out.println("Error");
            }
        } else
            System.out.println(new String(data));
    }
}

class ShiftStrategy implements Strategy {

    @Override
    public void encrypt(char[] str, int shift) {
        for (int i = 0; i < str.length; i++){
            if (str[i] >= 'A' && str[i] <= 'Z')
                str[i] = (char) ((str[i] - 'A' + shift) % 26 + 'A');
            else if (str[i] >= 'a' && str[i] <= 'z')
                str[i] = (char) ((str[i] - 'a' + shift) % 26 + 'a');
        }
    }

    @Override
    public void decrypt(char[] str, int shift) {
        encrypt(str, 26 - shift);
    }
}

class UnicodeStrategy implements Strategy {

    @Override
    public void encrypt(char[] str, int shift) {
        for (int i = 0; i < str.length; i++)
            str[i] = (char) ((str[i] + shift) % 256);
    }

    @Override
    public void decrypt(char[] str, int shift) {
        for (int i = 0; i < str.length; i++)
            str[i] = (char) ((256 + str[i] - shift) % 256);
    }
}
