package class04;

public class C04_SubArrayMaxSumFollowUp {

    /**
     * 返回一个数组中所选数字不能相邻的情况下最大子序列累加和
     */

    public static int robDP(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        // max sum when pick i
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        // 情况分析
        // 1. not contains arr[i], dp[i] = dp[i - 1];
        // 2. only contains arr[i], dp[i] = arr[i];
        // 3. contains arr[i] and before, dp[i] = arr[i] + dp[i - 2]
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
        }
        return dp[arr.length - 1];
    }
}
