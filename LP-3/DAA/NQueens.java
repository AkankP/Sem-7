//Practical No:05

import java.util.Scanner;

public class NQueens {
    public static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check the column on the left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left side
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    System.out.print("[Q]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    public static void solveNQueens(int[][] board, int col, int n) {
        if (col == n) {
            printBoard(board, n);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isSafe(board, i, col, n)) {
                board[i][col] = 1;
                solveNQueens(board, col + 1, n);
                board[i][col] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the value of N: ");
        int n = sc.nextInt();
        sc.close();

        int[][] board = new int[n][n];
        solveNQueens(board, 0, n);
        System.out.println("--------All possible solutions--------");
    }
}


/*
Time Complexity: O(N!)
Auxiliary Space: O(N^2)
*/