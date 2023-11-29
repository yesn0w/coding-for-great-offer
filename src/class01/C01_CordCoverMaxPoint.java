package class01;

import java.util.Arrays;

import static generic.MatchingMachine.generateSortedArray;

public class C01_CordCoverMaxPoint {

    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
     * 即使绳子边缘处盖住点也算盖住
     */

    /**
     * 滑动窗口
     */

    public static int maxPointSlidingWindow(int[] arr, int K) {
        int res = 0;
        int l = 0;
        int r = 0;
        for (; r < arr.length; r++) {
            while (l < r && arr[l] < arr[r] - K) {
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    public static int maxPointBF(int[] arr, int K) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= K) {
                pre--;
            }
            res = Math.max(res, i - pre);
        }
        return res;
    }



    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int times = 1000;
        for (int i = 0; i < times; i++) {
            int K = (int) (Math.random() * max);
            int[] arr = generateSortedArray(len, max);
            int res1 = maxPointSlidingWindow(arr, K);
            int res2 = maxPointBF(arr, K);
            if (res1 != res2) {
                System.out.println("出错了！");
                break;
            }
        }
    }
}
