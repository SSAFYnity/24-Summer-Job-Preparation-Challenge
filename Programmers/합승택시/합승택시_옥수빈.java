import java.util.*;
import java.awt.Point;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
                Map<Integer, List<Point>> map = new HashMap<>();

        for (int[] fare : fares) {
            int x = fare[0];
            int y = fare[1];
            int d = fare[2];

            map.computeIfAbsent(x, k -> new ArrayList<>()).add(new Point(y, d));
            map.computeIfAbsent(y, k -> new ArrayList<>()).add(new Point(x, d));
        }

        int[][] cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
            dijkstra(map, cost, i, n);
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, cost[s][i] + cost[i][a] + cost[i][b]);
        }

        return answer;
    }
    
    private void dijkstra(Map<Integer, List<Point>> map, int[][] cost, int start, int n) {
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.y));
        pq.add(new Point(start, 0));
        cost[start][start] = 0;

        while (!pq.isEmpty()) {
            Point now = pq.poll();
            int cur = now.x;
            int curCost = now.y;

            if (curCost > cost[start][cur]) continue;

            if (map.containsKey(cur)) {
                for (Point next : map.get(cur)) {
                    int nextNode = next.x;
                    int nextCost = curCost + next.y;

                    if (nextCost < cost[start][nextNode]) {
                        cost[start][nextNode] = nextCost;
                        pq.add(new Point(nextNode, nextCost));
                    }
                }
            }
        }
    }
}