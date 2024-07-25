import java.util.*;
import java.io.*;

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

        int mnLength = Integer.MAX_VALUE;
        int prefix = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int val = arr[i];
            prefix += val;
            queue.add(val);
            while(prefix >= S && !queue.isEmpty()){
                mnLength = Math.min(mnLength,queue.size());
                prefix -= queue.poll();
            }
        }
        System.out.println(mnLength == Integer.MAX_VALUE ? 0 : mnLength);
    }
}

