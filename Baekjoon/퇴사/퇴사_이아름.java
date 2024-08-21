import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] t, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new int[N];
        p = new int[N];
        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int nextDay = i + t[i];
            if (nextDay <= N) {
                dp[nextDay] = Math.max(dp[nextDay], dp[i] + p[i]);
            }
            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
        }

        System.out.println(dp[N]);
    }
}