import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] min_s = new int[]{0, 0, 1, 7, 4, 2, 0, 8};
        long[] dp = new long[100 + 4];

        Arrays.fill(dp, Long.MAX_VALUE / 2);
        for (int i = 2; i < min_s.length; i++) {
            dp[i] = min_s[i];
            if (i == 6) dp[i] = 6;
        }

        for (int i = 8; i <= 100; i++) {
            for (int j = 2; j < min_s.length; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] * 10 + min_s[j]);
            }
        }

        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        String space = " ";
        while (--T >= 0) {
            int num = Integer.parseInt(br.readLine());
            int div = num / 2;
            StringBuilder sb = new StringBuilder();
            if (num % 2 == 1) {
                div--;
                sb.append(min_s[3]);
            }

            while (--div >= 0) {
                sb.append(min_s[2]);
            }
            ans.append(dp[num]).append(space).append(sb.toString()).append("\n");
        }
        System.out.print(ans.toString());
    }
}
