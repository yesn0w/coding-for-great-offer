package class01;

public class C03_Near2Power {

    /**
     * 给定一个非负整数num，如何不用循环语句，返回>=num，并且离num最近的，2的某次方
     */

    /**
     * 位运算
     */

    public static int tableSizeFor(int n) {
        // corner case: 如果这个数恰好是 1 2 4 8 16...
        n--;
        // 把所有位都变成1，也可叫做：全填成1
        n |= (n >>> 1);
        n |= (n >>> 2);
        n |= (n >>> 4);
        n |= (n >>> 8);
        n |= (n >>> 16);
        // 注意n < 0时的处理（如果第32位是1）
        return (n < 0) ? 1 : n + 1;
    }
}
