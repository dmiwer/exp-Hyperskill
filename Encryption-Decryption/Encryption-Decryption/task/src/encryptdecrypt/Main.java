package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String mode = "enc";
        int shift = 0;
        char[] data = new char[0];
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
            }
        }

        if (mode.equals("enc"))
            encrypt(data, shift);
        else if (mode.equals("dec"))
            decrypt(data, shift);

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
