package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LCA2_이진곤 {
    static int N = -1;
    static ArrayList<Integer>[] adjList;
    static int[][] parentList;
    static int[] depth;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        adjList = new ArrayList[N];
        parentList = new int[N][];
        depth = new int[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adjList[a].add(b);
            adjList[b].add(a);
        }

        visited[0] = true;
        parentList[0] = new int[1];
        traversalTree(0, 0, -1);

        int M = Integer.parseInt(in.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            visited = new boolean[N];
            sb.append(findCommonParent(a, b) + 1).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }

    static void traversalTree(int curNode, int d, int prevNode) {
        depth[curNode] = d;
        if (d > 0) {
            parentList[curNode] = new int[(int) (Math.log(d) / Math.log(2)) + 1];
        }
        parentList[curNode][0] = prevNode;
        for (int i = 1; i < parentList[curNode].length; i++) {
            if (parentList[curNode][i - 1] != -1) {
                parentList[curNode][i] = parentList[parentList[curNode][i - 1]][i - 1];
            }
        }
        for (int next : adjList[curNode]) {
            if (!visited[next]) {
                visited[next] = true;
                traversalTree(next, d + 1, curNode);
            }
        }
    }

    static int findCommonParent(int a, int b) {
        if (depth[b] > depth[a]) {
            int temp = b;
            b = a;
            a = temp;
        }

        int gap = depth[a] - depth[b];
        for (int i = 0, num = 1; num < gap; num <<= 1) {
            if ((num & gap) >= 1) {
                a = parentList[a][i];
            }
            i++;
        }

        while (depth[a] != depth[b]) {
            if (depth[a] > depth[b]) {
                a = parentList[a][0];
            }
            else {
                b = parentList[b][0];
            }
        }

        if (a == b) {
            return a;
        }

        while (a != b) {
            for (int i = parentList[a].length - 1; i >= 0; i--) {
                if (i == 0 && parentList[a][i] == parentList[b][i]) {
                    return parentList[a][0];
                }
                if (parentList[a][i] != parentList[b][i]) {
                    a = parentList[a][i];
                    b = parentList[b][i];
                    break;
                }
            }
        }
        return -1;
    }
}