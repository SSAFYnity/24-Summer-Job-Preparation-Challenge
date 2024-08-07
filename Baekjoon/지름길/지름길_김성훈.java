import java.io.*;
import java.util.*;

public class Main {

    static class Road {
        int start, end, cost;

        Road(int _start, int _end, int _cost) {
            start = _start; end = _end; cost = _cost;
        }
    }

    static class Info {

        int loc, cost;

        Info(int _loc, int _cost) {
            loc = _loc; cost = _cost;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int n, d;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        Road[] roads = new Road[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            roads[i] = new Road(start, end, cost);
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        pq.add(new Info(0, 0));
        int result = d;

        while (!pq.isEmpty()) {

            Info cur = pq.poll();

            if (cur.loc > d) continue;
            if (cur.loc == d) {
                result = Math.min(result, cur.cost);
                break;
            }

            for (Road road : roads) {

                if (road.start < cur.loc) continue;
                if (road.end > d) continue;

                int nextCost = cur.cost + (road.start - cur.loc) + road.cost;
                // 현재 위치까지의 비용 + 지름길 까지의 거리 + 지름길의 비용
                int nextLoc = road.end;

                pq.add(new Info(nextLoc, nextCost));
                
                pq.add(new Info(d, nextCost + d - nextLoc));
                // 현재 위치까지의 비용 + 현재 위치부터 목적지까지의 비용
            }
        }
        System.out.println(result);
    }
}