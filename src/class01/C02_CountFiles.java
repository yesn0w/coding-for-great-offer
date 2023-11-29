package class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class C02_CountFiles {

    /**
     * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
     */

    /**
     * 遍历：DFS/BFS
     */

    public static int getFileNumber(String folderPath) {
        File root = new File(folderPath);
        if (!root.isDirectory() && !root.isFile()) {
            return 0;
        }
        if (root.isFile()) {
            return 1;
        }
        Queue<File> q = new LinkedList<>();
        int res = 0;
        q.add(root);
        while (!q.isEmpty()) {
            File curFolder = q.poll();
            for (File next : curFolder.listFiles()) {
                if (next.isFile()) {
                    res++;
                }
                else if (next.isDirectory()){
                    q.offer(next);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String path = "/Users/richard/Desktop";
        System.out.println(getFileNumber(path));
    }
}
