import java.util.*;
import java.io.*;

class Main {

    static class Node {

        int idx;
        int parentIdx;
        List<Integer> children = new ArrayList<>();

        public Node(int idx) {
            this.idx = idx;
        }

        public void setParent(int parentIdx) {
            this.parentIdx = parentIdx;
        }

        public void addChild(int childIdx) {
            children.add(childIdx);
        }
    }

    static int n;
    static int target1;
    static int target2;
    static Node[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());
        tree = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new Node(i);
        }
        visited = new boolean[n + 1];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parentIdx = Integer.parseInt(st.nextToken());
            int childIdx = Integer.parseInt(st.nextToken());
            tree[childIdx].setParent(parentIdx);
            tree[parentIdx].addChild(childIdx);
        }

        find(target1, target2, 0);

        System.out.println(-1);
    }

    private static void find(int cur, int target, int times) {
        if (cur == target) {
            System.out.println(times);
            System.exit(0);
        }
        Node curNode = tree[cur];
        // 부모 순회
        if (!visited[curNode.parentIdx] && curNode.parentIdx != 0) {
            visited[curNode.parentIdx] = true;
            find(curNode.parentIdx, target, times + 1);
        }
        // 자식 순회
        for (int childIdx : curNode.children) {
            if (!visited[childIdx]) {
                visited[childIdx] = true;
                find(childIdx, target, times + 1);
            }
        }
    }
}