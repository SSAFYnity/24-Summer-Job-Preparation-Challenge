import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 지름길_신유진 {
    public static class Road {
        int sp;
        int value;

        Road(int sp, int value) {
            this.sp = sp;
            this.value = value;
        }
    }

    static int N, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        ArrayList<Road>[] roads = new ArrayList[D + 1];
        for (int i = 0; i < D + 1; i++) {
            roads[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sp = Integer.parseInt(st.nextToken());
            int ep = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (ep > D)
                continue;
            if (ep - sp < value)
                continue;
            roads[ep].add(new Road(sp, value));
        }

        int[] dp = new int[D + 1];
        for (int i = 0; i <= D; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= D; i++) {
            dp[i] = dp[i - 1] + 1;

            for (Road road : roads[i]) {
                if (dp[i] > dp[road.sp] + road.value) {
                    dp[i] = dp[road.sp] + road.value;
                }
            }
        }
        System.out.println(dp[D]);

    }
}
