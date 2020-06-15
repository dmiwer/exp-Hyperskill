package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[] ans = "we found a treasure!".toCharArray();
        for (int i = 0; i < ans.length; i++)
            if (ans[i] > 96 && ans[i] < 123)
                ans[i] = (char) (219 - ans[i]);

        System.out.println(new String(ans));
    }
}
