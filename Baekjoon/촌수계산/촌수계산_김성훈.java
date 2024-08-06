import java.io.*;
import java.util.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, goalX, goalY;
    static List<Integer>[] graph;
    static int[] dist;

    static void input() throws IOException{

        n = Integer.parseInt(br.readLine());
        graph = new List[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        goalX = Integer.parseInt(st.nextToken());
        goalY = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

    }

    static void bfs(int s) {

        Queue<Integer> que = new LinkedList<>();
        que.add(s);
        dist[s] = 1;

        while (!que.isEmpty()) {

            int cur = que.poll();
            for (int i: graph[cur]) {
                if (dist[i] == 0) {
                    que.add(i);
                    dist[i] = dist[cur] + 1;
                }
            }
        }
    }

    static void pro() {
        bfs(goalX);
        System.out.println(dist[goalY] - 1);
    }

    public static void main(String[] args) throws IOException{
        input();
        pro();
    }
}
