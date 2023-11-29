package class01;

public class C04_MinSwapStep {

    /**
     * 一个数组中只有两种字符'G'和'B'，可以让所有的G都放在左侧，所有的B都放在右侧
     * 或者可以让所有的G都放在右侧，所有的B都放在左侧，但是只能在相邻字符之间进行交换操作，返回至少需要交换几次
     */

    public static int minStep(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int res1 = 0;
        // 让所有的G在左
        int index = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'G') {
                res1 += (i - index++);
            }
        }

        // 让所有的B在左
        int res2 = 0;
        index = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'B') {
                res2 += (i - index++);
            }
        }

        return Math.min(res1, res2);
    }
}
