package 지름길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 지름길_이승헌 {
    static PriorityQueue<Node> fast = new PriorityQueue<>();
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            if (end > M || end - start < dis) {
                continue;
            }
            fast.add(new Node(start, end, dis));
        }
        System.out.println(solve());
    }

    private static int solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        int[] dp = new int[M + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int nextDis;
        Node firstLoad;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.start == M) {
                return cur.distance;
            }

            while (!fast.isEmpty() && fast.peek().start < cur.start) {
                fast.poll();
            }
            firstLoad = fast.peek();

            for (Node nextLoad : fast) {
                if (nextLoad.start > firstLoad.end) {
                    continue;
                }

                nextDis = cur.distance + nextLoad.distance + (nextLoad.start - cur.start);
                if (cur.start > nextLoad.start || dp[nextLoad.end] < nextDis) {
                    continue;
                }
                dp[nextLoad.end] = nextDis;
                pq.offer(new Node(nextLoad.end, 0, nextDis));// 다음 지름길까지 더함
            }

            if (dp[M] > cur.distance + M - cur.start) {
                dp[M] = cur.distance + M - cur.start;
                pq.offer(new Node(M, 0, dp[M]));
            }
        }

        return M;
    }

    private static class Node implements Comparable<Node> {
        int start;
        int end;
        int distance;

        public Node(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            if (start == o.start) {
                return distance - o.distance;
            }
            return start - o.start;
        }
    }
}

