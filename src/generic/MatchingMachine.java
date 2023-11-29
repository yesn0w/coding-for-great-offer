package generic;

import java.util.Arrays;

public class MatchingMachine {

    public static int[] generateSortedArray(int len, int max) {
        int[] res = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * max);
        }
        Arrays.sort(res);
        return res;
    }

}
