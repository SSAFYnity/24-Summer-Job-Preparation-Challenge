import java.util.*;

class Solution {
    static List<List<Node>> list;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 인접리스트 초기화
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        int length = fares.length;
        for (int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];

            list.get(c).add(new Node(d, f));
            list.get(d).add(new Node(c, f));
        }
        int[] startS = dijkstra(s);
        int[] startA = dijkstra(a);
        int[] startB = dijkstra(b);
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (startS[i] != Integer.MAX_VALUE && startA[i] != Integer.MAX_VALUE && startB[i] != Integer.MAX_VALUE) {
                int cost = startS[i] + startA[i] + startB[i];
                minCost = Math.min(minCost, cost);
            }
        }
        int answer = minCost;
        return answer;
    }
    
    private static int[] dijkstra(int s) {
        int[] dist = new int[list.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[list.size()];
        dist[s] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s,0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int curNode = node.node;
            int curCost = node.cost;
            visited[curNode] = true;
            for(Node neighbor  : list.get(curNode)) {
                if(!visited[neighbor.node] && dist[neighbor.node] > curCost + neighbor.cost) {
                    dist[neighbor.node] = curCost + neighbor.cost;
                    pq.add(new Node(neighbor.node, curCost + neighbor.cost));
                    
                }
            }
        }
        return dist;
    }
    
    static class Node implements Comparable<Node>{
        int node;
        int cost;

        Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}
