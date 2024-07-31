import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        long[] arr = new long[101];
        Arrays.fill(arr, Long.MAX_VALUE);
        arr[2] = 1;
        arr[3] = 7;
        arr[4] = 4;
        arr[5] = 2;
        arr[6] = 6;
        arr[7] = 8;
        arr[8] = 10;

        String[] add = {"1", "7", "4", "2", "0", "8"};

        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            for (int k = 9; k <= n; k++) {
                for (int j = 2; j <= 7; j++) {
                    String line = String.valueOf(arr[k - j] + add[j - 2]);
                    arr[k] = Math.min(Long.parseLong(line), arr[k]);
                }
            }
            sb.append(arr[n]).append(" ").append(arr[n % 2 + 2]);
            for (int j = 0; j < (n / 2) - 1; j++) {
                sb.append(1);
            }

            System.out.println(sb);
        }
    }
}