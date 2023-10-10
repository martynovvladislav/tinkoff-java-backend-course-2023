package edu.hw1;

public class Task8 {
    private Task8() {
    }

    @SuppressWarnings("checkstyle:MagicNumber") public static boolean knightBoardCapture(int[][] board) {
        int[][] diffs =
            new int[][] {new int[] {1, 2}, new int[] {2, 1}, new int[] {-1, 2}, new int[] {2, -1}, new int[] {-2, 1},
                new int[] {1, -2}, new int[] {-1, -2}, new int[] {-2, -1}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                for (int k = 0; k < diffs.length; k++) {
                    int x = i + diffs[k][0];
                    int y = j + diffs[k][1];
                    if (x >= 0 && x < board.length && y >= 0 && y < board.length && board[i][j] == 1
                        && board[x][y] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
