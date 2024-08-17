import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] dp = new int[10001];

        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = 2; i < dp.length; i++) {
            dp[i] += dp[i - 2];
        }

        for (int i = 3; i < dp.length; i++) {
            dp[i] += dp[i - 3];
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
