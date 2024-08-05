import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 촌수계산_신유진 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sp = Integer.parseInt(st.nextToken());
        int ep = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        int[][] adj = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];
        boolean[] visit = new boolean[N + 1];
        q.add(sp);

        while (!q.isEmpty()) {
            int tmp = q.poll();

            if (visit[tmp])
                continue;

            visit[tmp] = true;

            for (int i = 1; i <= N; i++) {
                if (adj[tmp][i] == 0)
                    continue;

                result[i] = result[tmp] + 1;
                q.add(i);

                if (i == ep)
                    break;

            }

            if (result[ep] != 0)
                break;
        }

        System.out.println(Arrays.toString(result));
        System.out.println(result[ep] == 0 ? -1 : result[ep]);
    }
}
