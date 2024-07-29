import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
        }


        int MAX = (int) 2e6+1;
        int K = MAX;
        int L = 0;
        int R = 1;
        while(L<R && R<=N) {
            if(arr[R]-arr[L]>=S) K = Math.min(K,R-L++);
            else R++;
        }
        System.out.println(K==MAX ? 0 : K);
    }
}
