package class01;

import java.util.Arrays;

public class C06_AOE {

    /**
     * 给定两个非负数组x和hp，长度都是N，再给定一个正数range
     * x有序，x[i]表示i号怪兽在x轴上的位置
     * hp[i]表示i号怪兽的血量
     * 再给定一个正数range，表示如果法师释放技能的范围长度
     * 被打到的每只怪兽损失1点血量。返回要把所有怪兽血量清空，至少需要释放多少次AOE技能？
     */

    /**
     * 贪心，线段树
     */

    /**
     *
     * @param x 怪兽位置
     * @param hp 怪兽血量
     * @param range AOE范围：这里代表的是【直径】
     * @return 最小次数
     */
    public static int minAoeBF(int[] x, int[] hp, int range) {
        boolean allClear = true;
        for (int i = 0; i < hp.length; i++) {
            if (hp[i] > 0) {
                allClear = false;
                break;
            }
        }

        if (allClear) {
            return 0;
        }
        else {
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < x.length; i++) {
                if (hasHp(i, x, hp, range)) {
                    minus1Hp(i, x, hp, range);
                    res = Math.min(res, 1 + minAoeBF(x, hp, range));
                    add1Hp(i, x, hp, range);
                }
            }
            return res;
        }
    }

    private static void add1Hp(int index, int[] x, int[] hp, int range) {
        for (int i = index; i < x.length && x[i] - x[index] <= range; i++) {
            hp[i]--;
        }
    }

    private static void minus1Hp(int index, int[] x, int[] hp, int range) {
        for (int i = index; i < x.length && x[i] - x[index] <= range; i++) {
            hp[i]++;
        }
    }

    private static boolean hasHp(int index, int[] x, int[] hp, int range) {
        for (int i = index; i < x.length && x[i] - x[index] <= range; i++) {
            if (hp[i] > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 贪心算法，时间复杂度是O(N*range)
     * @param x
     * @param hp
     * @param range
     * @return
     */
    public static int minAoeGreedy(int[] x, int[] hp, int range) {
        int res = 0;

        int[] cover = new int[x.length];
        int start = 0;
        for (int i = 0; i < x.length; i++) {
            while (start < x.length && x[start] - x[i] <= range) {
                start++;
            }
            cover[i] = start;
        }


        for (int i = 0; i < x.length; i++) {
            if (hp[i] > 0) {
                int toMinus = hp[i];
                for (int j = i; j < cover[i]; j++) {
                    hp[j] -= toMinus;
                }
//                for (int j = i; j < x.length && x[j] - x[j] <= range; j++) {
//                    if (hp[j] > 0) {
//                        hp[j] -= toMinus;
//                    }
//                }
                res += toMinus;
            }
        }

        return res;
    }

    public static int minAoeSegmentTree(int[] x, int[] hp, int range) {
        long res = 0;
        int[] cover = new int[x.length];
        int start = 0;
        for (int i = 0; i < x.length; i++) {
            while (start < x.length && x[start] - x[i] <= range) {
                start++;
            }
            cover[i] = start - 1;
        }
        SegmentTree st = new SegmentTree(hp);
        st.build(1, x.length, 1);
        for (int i = 1; i <= x.length; i++) {
            long curHp = st.query(i, i, 1, hp.length, 1);
            if (curHp > 0) {
                res += curHp;
                // todo: 为什么这里应该是 cover[i - 1] + 1, 为什么要 +1？
                // 答案：因为所有的坐标都右移了一位，所以要加1
                st.add(i, cover[i - 1], (int)-curHp, 1, hp.length, 1);
            }
        }
        return (int)res;
    }


    public static class SegmentTree {
        private int MAXN;
        private int[] arr;
        private int[] lazy;
        private int[] sum;

        public SegmentTree(int[] origin) {
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
            lazy = new int[MAXN << 2];
            sum = new int[MAXN << 2];
        }

        public void build(int l, int r, int index) {
            if (l == r) {
                sum[index] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, index << 1);
            build(mid + 1, r, index << 1 | 1);
            pushUp(index);
        }

        public void add(int L, int R, int C, int l, int r, int index) {
            if (L <= l && R >= r) {
                sum[index] += C * (l - r + 1);
                lazy[index] += C;
                return;
            }

            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, index << 1);
            }
            if (R >= mid + 1) {
                add(L, R, C, mid + 1, r, index << 1 | 1);
            }
            pushUp(index);
        }

        public void pushDown(int index, int ln, int rn) {
            if (lazy[index] != 0) {
                lazy[index << 1] += lazy[index];
                lazy[index << 1 | 1] += lazy[index];
                sum[index << 1] += lazy[index] * ln;
                sum[index << 1 | 1] += lazy[index] * rn;
                lazy[index] = 0;
            }
        }

        public long query(int L, int R, int l, int r, int index) {
            if (L <= l && R >= r) {
                return sum[index];
            }
            int mid = (l + r) >> 1;
            pushDown(index, mid - l + 1, r - mid);
            long res = 0;
            if (L <= mid) {
                res += query(L, R, l, mid, index << 1);
            }
            if (R >= mid + 1) {
                res += query(L, R, mid + 1, r, index << 1 | 1);
            }
            return res;
        }

        public void pushUp(int index) {
            sum[index] = sum[index << 1] + sum[index << 1 | 1];
        }
    }

    public static int[] generateArray(int max, int len) {
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = (int)(Math.random() * max + 1);
        }
        return res;
    }

    public static int[] copyArray(int[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int lenMax = 50;
        int hpMax = 30;
        int xMax = 50;
        int rangeMax = 30;
        int testTime = 100;
        for (int i = 0; i < testTime; i++) {
            int len = (int)(Math.random() * lenMax + 1);
            int[] x1 = generateArray(xMax, len);
            Arrays.sort(x1);
            int[] hp1 = generateArray(hpMax, len);
            int[] x2 = copyArray(x1);
            int[] hp2 = copyArray(hp1);
            int range = (int)(Math.random() * rangeMax + 1);
//            System.out.println(minAoeGreedy(x1, hp1, range));
//            System.out.println(minAoeSegmentTree(x2, hp2, range));
            if (minAoeGreedy(x1, hp1, range) != minAoeSegmentTree(x2, hp2, range)) {
                System.out.println("Oops! Wrong!");
                return;
            }
        }
        System.out.println("Nice!");
    }
}
