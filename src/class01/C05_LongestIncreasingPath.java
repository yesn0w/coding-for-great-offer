package class01;

public class C05_LongestIncreasingPath {

    /**
     * 给定一个二维数组matrix，你可以从任何位置出发，走向上、下、左、右四个方向，返回能走出来的最长的递增链长度
     */

    public static int longestIncreasingPath(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, process1(i, j, matrix));
            }
        }
        return res;
    }

    public static int process1(int i, int j, int[][] matrix) {
        // 可省略，因为下面已经检验过了
//        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
//            return 0;
//        }
        int left = i > 0 && matrix[i - 1][j] > matrix[i][j] ? process1(i - 1, j, matrix) : 0;
        int right = i < (matrix.length - 1) && matrix[i + 1][j] > matrix[i][j] ? process1(i - 1, j, matrix) : 0;
        int up = j > 0 && matrix[i - 1][j] > matrix[i][j] ? process1(i - 1, j, matrix) : 0;
        int down = j < (matrix[0].length - 1) && matrix[i - 1][j] > matrix[i][j] ? process1(i - 1, j, matrix) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }


    public static int longestIncreasingPathCache(int[][] matrix) {
        int res = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, process2(i, j, matrix, dp));
            }
        }
        return res;
    }

    public static int process2(int i, int j, int[][] matrix, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int left = i > 0 && matrix[i - 1][j] > matrix[i][j] ? process2(i - 1, j, matrix, dp) : 0;
        int right = i < (matrix.length - 1) && matrix[i + 1][j] > matrix[i][j] ? process2(i - 1, j, matrix, dp) : 0;
        int up = j > 0 && matrix[i - 1][j] > matrix[i][j] ? process2(i - 1, j, matrix, dp) : 0;
        int down = j < (matrix[0].length - 1) && matrix[i - 1][j] > matrix[i][j] ? process2(i - 1, j, matrix, dp) : 0;
        int res = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[i][j] = res;
        return res;
    }
}
