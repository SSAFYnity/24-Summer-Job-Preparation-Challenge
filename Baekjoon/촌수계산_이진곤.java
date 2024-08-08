import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 촌수계산_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int targetA = Integer.parseInt(st.nextToken());
        int targetB = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        int m = Integer.parseInt(in.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int numA = Integer.parseInt(st.nextToken());
            int numB = Integer.parseInt(st.nextToken());
            adjList[numA].add(numB);
            adjList[numB].add(numA);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { targetA, 0 });
        boolean[] visited = new boolean[n + 1];
        visited[targetA] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == targetB) {
                answer = cur[1];
                break;
            }
            for (int next : adjList[cur[0]]) {
                if (!visited[next]) {
                    queue.offer(new int[] { next, cur[1] + 1 });
                    visited[next] = true;
                }
            }
        }
        System.out.println(answer);
    }
}