import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static long[] dist;
    static int N, M;
    static ArrayList<Node>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N + 1];
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, i));
            list[b].add(new Node(a, i));
        }

        dijkstra(1, N);
        System.out.println(dist[N]);
    }

    private static void dijkstra(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.n]) continue;
            visited[cur.n] = true;

            for (int k = 0; k < list[cur.n].size(); k++) {
                Node n = list[cur.n].get(k);
                if (visited[n.n]) continue;

                long order = cur.cost % M;
                order = n.cost - order;
                if (order < 0) order += M;
                long cost = order + cur.cost;

                if (dist[n.n] > cost) {
                    dist[n.n] = cost;
                    pq.offer(new Node(n.n, dist[n.n]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int n;
        long cost;

        Node(int n, long cost) {
            this.n = n;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(cost, o.cost);
        }
    }
}