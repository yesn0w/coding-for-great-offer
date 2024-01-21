package class03;

import java.util.Arrays;

public class C03_BoatsToSavePeople {

    public static int numRescueBoats(int[] arr, int limit) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int l = 0;
        int r = arr.length - 1;
        int res = 0;
        Arrays.sort(arr);
        while (l < r) {
            if (arr[l] + arr[r] > limit) {
                r--;
            }
            else {
                l++;
                r--;
            }
            res++;
        }
        if (l == r) {
            res++;
        }
        return res;
    }
}
