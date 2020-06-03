package tictactoe;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        char[] arr = scanner.nextLine().toCharArray();
        showField(arr);
        while(true) {
            System.out.print("Enter the coordinates: ");
            String s = scanner.nextLine();
            if(!Pattern.matches("^\\d+ \\d+$", s)) {
                System.out.println("You should enter numbers!");
                continue;
            }
            String[] ars = s.split(" ");
            int x = Integer.parseInt(ars[0]);
            int y = Integer.parseInt(ars[1]);
            if (x < 1 || x > 3 || y < 1 || y > 3){
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            int p = 8 + x - 3 * y;
            if(arr[p] == 'X' || arr[p] == 'O'){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            arr[p] = 'X';
            break;
        }
        showField(arr);
//        System.out.println(verdict(arr));
    }

    private static void showField(char[] arr) {
        System.out.println("---------");
        System.out.printf("| %c %c %c |%n", arr[0], arr[1], arr[2]);
        System.out.printf("| %c %c %c |%n", arr[3], arr[4], arr[5]);
        System.out.printf("| %c %c %c |%n", arr[6], arr[7], arr[8]);
        System.out.println("---------");
    }

    private static String verdict(char[] arr) {
        int countX = 0, countO = 0;
        int winX = 0, winO = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 'X') countX++;
            if(arr[i] == 'O') countO++;
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
