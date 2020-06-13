package converter;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static char[] arr = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ans;

        int oldBase = scanner.nextInt();
        scanner.skip("\\n");
        String num = scanner.nextLine();
        int newBase = scanner.nextInt();

        int n = fromOldBaseToInt(num, oldBase);
        if (newBase == 1) {
            char[] c = new char[n];
            Arrays.fill(c, '1');
            ans = new String(c);
        } else ans = fromIntToNewBase(n, newBase);

        System.out.println(ans);
    }

    private static String fromIntToNewBase(int n, int newBase) {
        return n == 0 ? "" : fromIntToNewBase(n / newBase, newBase) + arr[n % newBase];
    }

    private static int fromOldBaseToInt(String num, int oldBase) {
        int ans = 0;
        for(char c: num.toCharArray()){
            ans = ans * oldBase + Arrays.binarySearch(arr, c);
        }
        return ans;
    }
}
