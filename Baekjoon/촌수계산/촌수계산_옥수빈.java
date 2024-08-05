import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        List<Integer>[] list = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(a);
        int cost = 0;
        boolean[] visited = new boolean[n + 1];
        visited[a] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int cur = queue.poll();
                for (int next : list[cur]) {
                    if (visited[next]) continue;
                    if (next == b) {
                        System.out.println(cost + 1);
                        return;
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
            cost++;
        }

        System.out.println(-1);
    }
}