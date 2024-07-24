package 합승택시요금;
import java.util.Arrays;
import java.util.PriorityQueue;

public class 합승택시요금_신유진 {
    static class Vertex implements Comparable<Vertex> {
        int e, w;

        Vertex(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.w - o.w;
        }
    }

    static int[][] adj;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        adj = new int[n + 1][n + 1];
        for (int i = 0; i < fares.length; i++) {
            adj[fares[i][0]][fares[i][1]] = fares[i][2];
            adj[fares[i][1]][fares[i][0]] = fares[i][2];
        }

        int[] dijkstraS = dijkstra(s, n);
        int[] dijkstraA = dijkstra(a, n);
        int[] dijkstraB = dijkstra(b, n);

        answer = dijkstraS[a] + dijkstraS[b];
        for (int i = 1; i < n + 1; i++) {
            if (dijkstraS[i] != Integer.MAX_VALUE && dijkstraA[i] != Integer.MAX_VALUE && dijkstraB[i] != Integer.MAX_VALUE) {
                answer = Math.min(answer, dijkstraS[i] + dijkstraA[i] + dijkstraB[i]);
            }
        }

        return answer;
    }

    public static int[] dijkstra(int startIdx, int n) {
        boolean[] visit = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startIdx] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(startIdx, 0));

        while (!pq.isEmpty()) {
            Vertex tmp = pq.poll();

            if (visit[tmp.e])
                continue;

            visit[tmp.e] = true;
            for (int i = 1; i <= n; i++) {
                if (!visit[i] && adj[tmp.e][i] != 0 && dist[i] > dist[tmp.e] + adj[tmp.e][i]) {
                    dist[i] = dist[tmp.e] + adj[tmp.e][i];
                    pq.add(new Vertex(i, dist[i]));
                }
            }
        }

        return dist;
    }
}
