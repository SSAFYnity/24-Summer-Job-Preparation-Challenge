import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0, right = 0;
        long sum = 0;
        long ans = Long.MAX_VALUE;
        while (left < N) {
            if (right < N) {
                if (sum >= S) {
                    sum -= arr[left++];
                } else {
                    sum += arr[right++];
                }
            } else {
                sum -= arr[left++];
            }
            if (sum >= S) {
                ans = Math.min(ans, right - left);
            }
        }
        System.out.println(ans == Long.MAX_VALUE ? 0 : ans);
    }
}