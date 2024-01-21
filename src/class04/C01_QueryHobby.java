package class04;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class C01_QueryHobby {

    /*
     * 今日头条原题
     *
     * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)。意思是在数组里下标0~3这个范围上，有几个2？返回2。
     * 假设给你一个数组arr，对这个数组的查询非常频繁，请返回所有查询的结果
     *
     */

    public static class QueryBox {
        private HashMap<Integer, ArrayList<Integer>> map;
        public QueryBox(int[] arr) {
            map = new HashMap<Integer, ArrayList<Integer>>();
            for (int i = 0; i < arr.length; i++) {
                int cur = arr[i];
                if (map.containsKey(cur)) {
                    map.get(cur).add(i);
                }
                else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i);
                    map.put(cur, list);
                }
            }
        }

        public int query(int L, int R, int value) {
            if (!map.containsKey(value)) {
                return 0;
            }
            int lessL = countLess(map.get(value), L);
            int lessRPlus1 = countLess(map.get(value), R + 1);
            return lessRPlus1 - lessL;
        }

        public int countLess(ArrayList<Integer> list, int value) {
            int l = 0;
            int r = list.size() - 1;
            int res = -1;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (list.get(mid) < value) {
                    res = mid;
                    l = mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }
            return res + 1;
        }

    }
}
