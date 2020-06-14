package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[] arr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ans;

        int oldBase = scanner.nextInt();
        scanner.skip("\\n");
        String num = scanner.nextLine();
        int newBase = scanner.nextInt();

        double n = fromOldBaseToDouble(num, oldBase);
        if (newBase == 1) {
            char[] c = new char[(int) n];
            Arrays.fill(c, '1');
            ans = new String(c);
        } else ans = fromDoubleToNewBase(n, newBase);

        System.out.println(ans);
    }

    private static String fromDoubleToNewBase(double n, int newBase) {
        int f = 1;
        while (f <= n) f *= newBase;
        f /= newBase;
        StringBuilder ans = new StringBuilder();
        int intgr = (int) n;
        double fact = n - intgr;
        while (f > 0) {
            ans.append(arr[intgr / f]);
            intgr %= f;
            f /= newBase;
        }
        if (ans.toString().equals("")) ans = new StringBuilder("0");
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
        long ans = 0;
        int f = -1;
        for (char c : num.toCharArray()) {
            if (c == '.') {
                if (oldBase == 1) break;
                f = 0;
                continue;
            }
            ans = ans * oldBase + Arrays.binarySearch(arr, c);
            f = f < 0 ? f : f + 1;
        }
        return f < 0 ? ans : ans / Math.pow(oldBase, f);
    }
}
