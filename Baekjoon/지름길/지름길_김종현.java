import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stk.nextToken());
        int D = Integer.parseInt(stk.nextToken());

        int[][] a = new int[N][];
        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            int st = Integer.parseInt(stk.nextToken());
            int ed = Integer.parseInt(stk.nextToken());
            int dist = Integer.parseInt(stk.nextToken());
            a[i] = new int[]{st, ed, dist};
        }


        int[] dp = new int[D+1];
        // 지름길 없이 그대로 가는 경우 걸리는 시간.
        for (int i = 0; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= D; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            for (int j = 0; j < N; j++) {
                if (a[j][1] == i) {
                    dp[i] = Math.min(dp[i], dp[a[j][0]] + a[j][2]);
                }
            }
        }
        System.out.println(dp[D]);
    }
}
