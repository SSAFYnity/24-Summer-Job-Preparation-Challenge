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
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N + 1];
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += Integer.parseInt(st.nextToken());
            arr[i] = sum;
        }

        int start = 0;
        int end = 1;
        int answer = 100001;
        while (start < N || end <= N + 1) {
            if (arr[end] - arr[start] < S) {
                if (end < N)
                    end++;
                else
                    break;
            } else {
                answer = Math.min(answer, end - start);
                start++;
            }
        }

        if (answer == 100001)
            answer = 0;
        System.out.println(answer);
    }
}