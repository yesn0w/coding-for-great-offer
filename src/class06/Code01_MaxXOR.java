package class06;

public class Code01_MaxXOR {

    /*
    public static class Node {
        public Node[] nexts = new Node[2];
    }
     */


    public static class Trie {

        // 看起来写里面和写外面都行？
        class Node {
            Node[] nexts = new Node[2];
        }

        public Node root = new Node();

        public void add(int number) {
            Node cur = root;
            int index = 0;
            for (int move = 31; move >= 0; move--) {
                index = ((number >> move) & 1);
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
        }

        public int maxXor(int number) {
            Node cur = root;
            int index = 0;

            int res = 0;
            for (int move = 31; move >= 0; move--) {
                // 这个数第move位的值
                index = ((number >> move) & 1);
                // 希望遇到的值
                int best = move == 31 ? index : (index ^ 1);
                // 实际遇到的值
                int real = cur.nexts[best] == null ? (best ^ 1) : best;
                // 取^
                res |= (index ^ real) << move;
                cur = cur.nexts[real];
            }
            return res;
        }
    }

    public static int MaxXorTrie(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int xor = 0;
        Trie trie = new Trie();
        // 要把0加进去，是为了保证全选的情况
        trie.add(0);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            res = Math.max(res, trie.maxXor(xor));
            trie.add(xor);
        }
        return res;
    }

    /*
    public static int MaxXorTrie(int[] arr) {
        class Trie {

        }
        int xor = arr[0];
        for (int i = 0; i < arr.length; i++) {

        }
    }
     */

    // O(N^3)
    public static int MaxXorBF(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int xor = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    xor = xor ^ arr[k];
                }
                res = Math.max(res, xor);
            }
        }
        return res;
    }

    // 优化一点
    // O(N^2)
    public static int MaxXorBFOp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] xorArray = new int[arr.length];
        xorArray[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xorArray[i] = xorArray[i - 1] ^ arr[i];
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                res = Math.max(res, j == 0 ? xorArray[i] : xorArray[i] ^ xorArray[j - 1]);
            }
        }
        return res;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 10000;
        int maxSize = 20;
        int maxValue = 30;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int res1 = MaxXorBF(arr);
            int res2 = MaxXorBFOp(arr);
            int res3 = MaxXorTrie(arr);
            if (res1 != res2) {
                printArray(arr);
                System.out.println(res1);
                System.out.println(res2);
                System.out.println("res1 and res2 not same!");
                break;
            }
            if (res1 != res3) {
                printArray(arr);
                System.out.println(res1);
                System.out.println(res3);
                System.out.println("res1 and res3 not same!");
                break;
            }
            if (res2 != res3) {
                printArray(arr);
                System.out.println(res2);
                System.out.println(res3);
                System.out.println("res2 and res3 not same!");
                break;
            }
        }
    }

}
