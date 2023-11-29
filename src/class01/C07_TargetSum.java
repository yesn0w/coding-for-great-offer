package class01;

public class C07_TargetSum {

    public static int findTargetSumWaysRecursive(int[] arr, int s) {
        return process1(arr, 0, s);
    }

    public static int process1(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        return process1(arr, index + 1, target - arr[index]) +
                process1(arr, index + 1, target + arr[index]);
    }


    // 这样画表确实费劲，要考虑 target - sum ~ target + sum的范围，从0左右摇摆
    // 所以使用记忆化搜索的方式，放进map里
    /*
    public static int findTargetSumWaysDP(int[] arr, int s) {
        return process2(arr, 0, s);
    }

    public static int process2(int[] arr, int index, int )
    */


    /**
     * 接下来是最大优化
     * 转变成背包问题
     */
    public static int findTargetSumWaysNapSak(int[] arr, int s) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                arr[i] = -arr[i];
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum < s) {
            return 0;
        }

        if ((sum % 2 == 1 && s % 2 == 0) || (sum % 2 == 0 && s % 2 == 1)) {
            return 0;
        }

        int p = (s + sum) >> 1;

        int[][] dp = new int[arr.length + 1][p + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= s; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - arr[i - 1]];
                }
            }
        }
        return dp[arr.length][s];
        /**
         * int[] dpSpaceCompress = new int[p + 1];
         * dpSpaceCompress[0] = 1;
         * for (int n : arr) {
         *     for (int i = s; i >= n; i--) {
         *         dpSpaceCompress[i] += dpSpaceCompress[i - n];
         *     }
         * }
         * return dpSpaceCompress[s];
         */

    }

}
