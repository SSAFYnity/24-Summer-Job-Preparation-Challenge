import java.io.*;
import java.util.*;

public class boj2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(o -> o[0])));

        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {nums[n-1][i], n-1, i});
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int[] elem = pq.poll();
            res = elem[0];
            int x = elem[1];
            int y = elem[2];
            
            if (x > 0) {
                pq.offer(new int[] {nums[x-1][y], x-1, y});
            }
        }

        System.out.println(res);

    }

}
