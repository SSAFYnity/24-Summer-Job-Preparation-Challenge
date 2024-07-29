import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[]{1, 7, 4, 2, 0, 8};
        String[] max = new String[101];
        long[] min = new long[101];
        max[2] = "1";
        max[3] = "7";
        min[2] = 1;
        min[3] = 7;
        min[4] = 4;
        min[5] = 2;
        min[6] = 6;
        min[7] = 8;
        min[8] = 10;
        for (int i = 4; i < max.length; i++) {
            max[i] = max[i - 2] + "1";
        }
        for (int i = 9; i < min.length; i++) {
            min[i] = Long.MAX_VALUE;
            for (int j = 2; j <= 7; j++) {
                String s = "" + min[i - j] + arr[j - 2];
                min[i] = Math.min(Long.parseLong(s), min[i]);
            }
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(min[n]).append(" ").append(max[n]).append("\n");
        }
        System.out.println(sb);
    }
}