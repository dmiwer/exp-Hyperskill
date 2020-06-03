package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        char[] arr = new Scanner(System.in).nextLine().toCharArray();
        int i = 0;
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", arr[i++], arr[i++], arr[i++]);
        System.out.printf("| %c %c %c |%n", arr[i++], arr[i++], arr[i++]);
        System.out.printf("| %c %c %c |%n", arr[i++], arr[i++], arr[i++]);
        System.out.println("---------");
        System.out.println(verdict(arr));
    }

    private static String verdict(char[] arr) {
        int countX = 0, countO = 0;
        int winX = 0, winO = 0;
        for (char c : arr) {
            if (c == 'X') countX++;
            if (c == 'O') countO++;
        }
        if(arr[0] == arr[1] && arr[1] == arr[2] && arr[0] == 'X') winX++;
        if(arr[0] == arr[1] && arr[1] == arr[2] && arr[0] == 'O') winO++;
        if(arr[3] == arr[4] && arr[4] == arr[5] && arr[3] == 'X') winX++;
        if(arr[3] == arr[4] && arr[4] == arr[5] && arr[3] == 'O') winO++;
        if(arr[6] == arr[7] && arr[7] == arr[8] && arr[6] == 'X') winX++;
        if(arr[6] == arr[7] && arr[7] == arr[8] && arr[6] == 'O') winO++;
        if(arr[0] == arr[3] && arr[3] == arr[6] && arr[0] == 'X') winX++;
        if(arr[0] == arr[3] && arr[3] == arr[6] && arr[0] == 'O') winO++;
        if(arr[1] == arr[4] && arr[4] == arr[7] && arr[1] == 'X') winX++;
        if(arr[1] == arr[4] && arr[4] == arr[7] && arr[1] == 'O') winO++;
        if(arr[2] == arr[5] && arr[5] == arr[8] && arr[2] == 'X') winX++;
        if(arr[2] == arr[5] && arr[5] == arr[8] && arr[2] == 'O') winO++;
        if(arr[0] == arr[4] && arr[4] == arr[8] && arr[0] == 'X') winX++;
        if(arr[0] == arr[4] && arr[4] == arr[8] && arr[0] == 'O') winO++;
        if(arr[6] == arr[4] && arr[4] == arr[2] && arr[6] == 'X') winX++;
        if(arr[6] == arr[4] && arr[4] == arr[2] && arr[6] == 'O') winO++;
        if(Math.abs(countX - countO) > 1 || winO > 0 && winX > 0) return "Impossible";
        if(winO > 0) return "O wins";
        if(winX > 0) return "X wins";
        if(countO + countX == 9) return "Draw";
        return "Game not finished";
    }
}
