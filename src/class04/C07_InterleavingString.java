package class04;

public class C07_InterleavingString {

    /**
     * 给定三个字符串s1、s2、s3，请你帮忙验证s3是否是由s1和s2交错组成的
     * Leetcode题目：https://leetcode.com/problems/interleaving-string/
     */

    /**
     * 样本对应模型
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleaveDP(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        char[] c3 = s3.toCharArray();
        if (c1.length + c2.length != c3.length) {
            return false;
        }

        boolean[][] dp = new boolean[c1.length + 1][c2.length + 1];
        dp[0][0] = true;
        for (int j = 1; j < c2.length + 1; j++) {
            if (c2[j - 1] != c3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i < s1.length() + 1; i++) {
            if (c1[i - 1] != c3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if ((c3[i + j - 1] == c2[j - 1] && dp[i][j - 1])
                        || (c3[i + j - 1] == c1[i - 1] && dp[i - 1][j])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[c1.length][c2.length];
    }
}
