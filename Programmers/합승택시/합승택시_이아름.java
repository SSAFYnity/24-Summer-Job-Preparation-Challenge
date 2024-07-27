import java.io.*;
import java.util.*;

class Solution {

    static ArrayList<Node>[] list;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int[] fare : fares) {
            int ss = fare[0];
            int ee = fare[1];
            int cc = fare[2];
            list[ss].add(new Node(ee, cc));
            list[ee].add(new Node(ss, cc));
        }

        int[] total = dijkstra(n, s);
        int[] aRoute = dijkstra(n, a);
        int[] bRoute = dijkstra(n, b);

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, total[i] + aRoute[i] + bRoute[i]);
        }

        return answer;
    }

    static int[] dijkstra(int n, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost < dist[cur.next]) continue;

            for (int k = 0; k < list[cur.next].size(); k++) {
                Node next = list[cur.next].get(k);
                if (dist[next.next] > next.cost + dist[cur.next]) {
                    dist[next.next] = next.cost + dist[cur.next];
                    Node node = new Node(next.next, dist[next.next]);
                    pq.offer(node);
                }
            }
        }
        return dist;
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