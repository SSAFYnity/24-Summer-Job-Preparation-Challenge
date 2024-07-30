import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 횡단보도_이진곤 {
    static final long INF = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(new int[] { B, i });
            adjList[B].add(new int[] { A, i });
        }

        long[] dp = new long[N + 1];
        Arrays.fill(dp, INF);
        dp[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int unitTime = (int) (dp[cur] % M);
            for (int[] arr : adjList[cur]) {
                int next = arr[0];
                int time = arr[1];
                long nextTime;
                if (unitTime > time) {
                    nextTime = dp[cur] + M - (unitTime - time);
                }
                else {
                    nextTime = M * (dp[cur] / M) + time;
                }

                if (dp[next] > nextTime + 1) {
                    dp[next] = nextTime + 1;
                    queue.offer(next);
                }
            }
        }
        System.out.println(dp[N]);
    }
}