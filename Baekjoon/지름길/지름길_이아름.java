import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, D;
    static ArrayList<Node>[] list;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        list = new ArrayList[10001];
        dist = new int[10001];
        for (int i = 0; i <= 10000; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < D; i++) {
            list[i].add(new Node(i + 1, 1));
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b, c));
        }
        dijkstra(0);
        System.out.println(dist[D]);
    }

    private static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, dist[s]));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (D == cur.next) {
                return;
            }

            for (int k = 0; k < list[cur.next].size(); k++) {
                Node n = list[cur.next].get(k);
                if (dist[n.next] > n.cost + dist[cur.next]) {
                    dist[n.next] = n.cost + dist[cur.next];
                    pq.offer(new Node(n.next, dist[n.next]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int next, cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}