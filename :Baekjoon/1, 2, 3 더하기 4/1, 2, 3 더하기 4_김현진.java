import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 1, 2, 3 더하기 4_김현진 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(sc.readLine());
        int[] dp = new int[10001];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i=4; i<10001; i++){
            dp[i] = 1 + i/2 + dp[i-3];
        }

        for(int test_case=0; test_case<T; test_case++){
            int n = Integer.parseInt(sc.readLine());
            sb.append(dp[n]).append('\n');
        } System.out.println(sb);
    }
}