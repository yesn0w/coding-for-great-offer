package class04;

public class C03_SubMatrixMaxSum {

    /**
     * 返回一个二维数组中子矩阵最大累加和
     */

    /**
     * 时间复杂度：O(行^2*列)
     */

    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int M = m.length;
        int N = m[0].length;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < M; i++) {
            int[] sum = new int[N];
            for (int j = 0; j < N; j++) {
                sum[j] += m[i][j];
                // 这里其实就：如果之前的累加和小于0，那就是他自己了，如果有之前的，那就连带之前和自己的，进行计算
                cur += sum[j];
                max = Math.max(max, cur);
                cur = cur < 0 ? 0 : cur;
            }
        }
        return max;
    }
}
