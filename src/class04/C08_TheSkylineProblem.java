package class04;

import java.util.*;

public class C08_TheSkylineProblem {

    /**
     * 大楼轮廓线问题
     * Leetcode题目：https://leetcode.com/problems/the-skyline-problem/
     */

    public static class Node {
        public int x;
        public boolean isAdd;
        public int h;

        public Node(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            nodes[i * 2] = new Node(buildings[i][0], true, buildings[i][2]);
            nodes[i * 2 + 1] = new Node(buildings[i][0], false, buildings[i][2]);
        }
        Arrays.sort(nodes, new NodeComparator());
        // key: height, value: times
        TreeMap<Integer, Integer> heightTimeMap = new TreeMap<>();
        //
        TreeMap<Integer, Integer> heightMap = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) {
                if (!heightTimeMap.containsKey(nodes[i].h)) {
                    heightTimeMap.put(nodes[i].h, 1);
                }
                else {
                    heightTimeMap.put(nodes[i].h, heightTimeMap.get(nodes[i].h) + 1);
                }
            }
            else {
                if (heightTimeMap.get(nodes[i].h) == 1) {
                    heightTimeMap.remove(nodes[i].h);
                }
                else {
                    heightTimeMap.put(nodes[i].h, heightTimeMap.get(nodes[i].h) - 1);
                }
            }
            // 记录当前高度
            if (heightTimeMap.isEmpty()) {
                heightMap.put(nodes[i].x, 0);
            }
            else {
                heightMap.put(nodes[i].x, heightTimeMap.lastKey());
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : heightMap.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            // 不相等的时候（轮廓线有变化）才需要记录，相等的话前面的轮廓线已经包括了，所以不需要记录了
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != curMaxHeight) {
                res.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }

        return res;
    }
}
