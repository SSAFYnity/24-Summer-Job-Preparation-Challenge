import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long[] dp = new long[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 1000001; i++) {
            dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
        }

        for (int t = 0; t < n; t++) {
            bw.append(dp[Integer.parseInt(br.readLine())] + "\n");
        }

        bw.flush();
        bw.close();
    }
}

