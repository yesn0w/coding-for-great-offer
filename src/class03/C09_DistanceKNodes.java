package class03;

import generic.TreeElement.*;

import java.util.*;

public class C09_DistanceKNodes {

    public static List<Node> distanceKNodes(Node root, Node target, int K) {
        HashMap<Node, Node> parentMap = new HashMap<>();
        createParentMap(root, parentMap);
        List<Node> res = new LinkedList<>();

        Queue<Node> q = new LinkedList<>();
        q.offer(target);
        Set<Node> visited = new HashSet<>();
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Node cur = q.poll();
                visited.add(cur);
                if (level == K) {
                    res.add(cur);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    q.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    q.add(cur.right);
                }
                if (parentMap.get(cur) != null && !visited.contains(parentMap.get(cur))) {
                    q.add(parentMap.get(cur));
                }
            }
            level++;
            if (level > K) {
                break;
            }
        }
        return res;
    }

    private static void createParentMap(Node node, HashMap<Node, Node> parentMap) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parentMap.put(node.left, node);
            createParentMap(node.left, parentMap);
        }
        if (node.right != null) {
            parentMap.put(node.right, node);
            createParentMap(node.right, parentMap);
        }
    }

    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        Node root = n3;
        Node target = n5;
        int K = 2;

        List<Node> ans = distanceKNodes(root, target, K);
        for (Node o1 : ans) {
            System.out.println(o1.value);
        }

    }

}
