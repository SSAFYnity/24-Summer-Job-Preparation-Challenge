import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());

        List<int[]>[] list = new ArrayList[N + 1];

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());

            if(list[n1] == null) list[n1] = new ArrayList<>();
            list[n1].add(new int[]{n2, i});
            if(list[n2] == null) list[n2] = new ArrayList<>();
            list[n2].add(new int[]{n1, i});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1[1], n2[1]));

        pq.add(new long[]{1, 0});

        long[] dist = new long[N + 1];
        Arrays.fill(dist, (long) 7e10 + 4);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();

            for (int[] next : list[(int) curr[0]]) {
                long curr_t = curr[1] % M;
                long wait = curr_t > next[1] ? M-curr_t + next[1] : next[1] - curr_t;

                if (dist[next[0]] > curr[1] + wait) {
                    dist[next[0]] = curr[1] + wait;
                    pq.add(new long[]{next[0], dist[next[0]]});
                }
            }
        }
        System.out.println(dist[N]+1);
    }
}
