import java.io.*;
import java.util.*;

public class Main {

    static Queue<Integer> q = new LinkedList<>();
    static int[] visited;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new int[n+1];
        graph = new ArrayList[n+1];
        for (int i = 0; i<=n; i++){
            visited[i] = -1;
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        bfs(a);
        System.out.println(visited[b]);
    }

    static void bfs(int start) {
        q.add(start);
        visited[start] = 0;
        while (!q.isEmpty()) {
            int num = q.poll();
            for (int i = 0; i<graph[num].size(); i++) {
                int neighbor = graph[num].get(i);
                if (visited[neighbor]==-1) {
                    visited[neighbor] = visited[num] + 1;
                    q.add(neighbor);
                }
            }
        }
    }
}