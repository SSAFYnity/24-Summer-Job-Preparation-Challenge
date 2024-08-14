import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        String row = br.readLine();
        int[] value = Arrays.stream(row.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] nums = new int[100001];
        int answer = 0;
        int start = 0;
        int count = 0;
        for (int end = 0; end < N; end++) {
            int tmp = value[end];
            nums[tmp]++;
            if (nums[tmp] > K) {
                for (int j = start; j < end; j++) {
                    nums[value[j]]--;
                    start++;
                    if (value[j] == tmp) {
                        break;
                    }
                }
            }
            count = end - start + 1;
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }
}