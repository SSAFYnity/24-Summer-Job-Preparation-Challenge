import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = 0;
        int[] num = new int[100_001];
        int left = 0;
        int right = 0;
        int len = 1;
        num[arr[0]]++;
        while (left < n) {
            if (right + 1 < n) {
                if (num[arr[right + 1]] + 1 <= k) {
                    num[arr[++right]]++;
                    len++;
                } else {
                    ans = Math.max(ans, len);
                    len--;
                    num[arr[left++]]--;
                }
            } else {
                ans = Math.max(ans, len);
                len--;
                num[arr[left++]]--;
            }
        }
        System.out.println(ans);
    }
}