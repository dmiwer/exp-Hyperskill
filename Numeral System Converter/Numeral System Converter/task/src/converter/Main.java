package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[] arr = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ans;

        try {
            int oldBase = scanner.nextInt();
            scanner.skip("\\n");
            String num = scanner.nextLine();
            int newBase = scanner.nextInt();


            double n = fromOldBaseToDouble(num, oldBase);
            ans = fromDoubleToNewBase(n, newBase);
        } catch (Exception e) {
            System.out.println("error");
            return;
        }

        System.out.println(ans);
    }

    private static String fromDoubleToNewBase(double n, int newBase) {
        if (newBase < 1 || newBase > 36) throw new IllegalArgumentException();
        if (newBase == 1) {
            char[] c = new char[(int) n];
            Arrays.fill(c, '1');
            return new String(c);
        }
        int f = 1;
        while (f <= n) f *= newBase;
        StringBuilder ans = new StringBuilder("");
        int intgr = (int) n;
        double fact = n - intgr;
        while ((f /= newBase) > 0) {
            ans.append(arr[intgr / f]);
            intgr %= f;
        }
        if (ans.length() == 0) ans.append("0");
        if (fact > 0) {
            ans.append('.');
            for (int i = 0; i < 5; i++) {
                fact *= newBase;
                ans.append(arr[(int) fact]);
                fact -= (int) fact;
            }
        }
        return ans.toString();
    }

    private static double fromOldBaseToDouble(String num, int oldBase) {
        if (oldBase < 1 || oldBase > 36) throw new IllegalArgumentException();
        if (oldBase == 1) return num.split("\\.")[0].length();
        long ans = 0;
        int f = -1;
        int ch;
        for (char c : num.toCharArray()) {
            if (c == '.') {
                f = 0;
                continue;
            }
            if ((ch = Arrays.binarySearch(arr, c)) >= oldBase) throw new IllegalArgumentException();
            ans = ans * oldBase + ch;
            f = f < 0 ? f : f + 1;
        }
        return f < 0 ? ans : ans / Math.pow(oldBase, f);
    }
}
