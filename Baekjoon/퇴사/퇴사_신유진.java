import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            if (t + i - 1 > N)
                continue;

            for (int j = 0; j < i; j++) {
                dp[t + i - 1] = Math.max(dp[j] + p, dp[t + i - 1]);
                answer = Math.max(answer, dp[t + i - 1]);

            }
        }
        System.out.println(answer);
    }
}
