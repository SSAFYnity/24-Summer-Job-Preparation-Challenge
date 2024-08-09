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

        int len = N + 1;
        int sum = arr[0];
        int left = 0, right = 0;

        while (left <= right && right < N) {
            if (sum >= S) {
                len = Integer.min(len, right - left + 1);
                sum -= arr[left++];
            } else if (right == N - 1) break;
            else
                sum += arr[++right];
        }

        System.out.print(len < N + 1 ? len : 0);
    }
}