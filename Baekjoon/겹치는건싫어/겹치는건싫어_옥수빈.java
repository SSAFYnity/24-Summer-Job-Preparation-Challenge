import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        int[] count = new int[100001];
        int start = 0;
        int end = 0;
        while (end < N && start < N) {
            count[arr[end]]++;
            if (count[arr[end]] > K) {
                answer = Math.max(answer, end - start);
                count[arr[start]]--;
                count[arr[end]]--;
                start++;
            } else {
                end++;
            }
        }

        Arrays.sort(count);
        if (count[100000] <= K)
            answer = Math.max(answer, end - start);

        System.out.println(answer);
    }
}