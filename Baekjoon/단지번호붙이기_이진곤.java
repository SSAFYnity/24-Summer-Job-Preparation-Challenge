import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 단지번호붙이기_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String temp = in.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = temp.charAt(j) - '0';
                if (map[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }

        final int[] dy = new int[] { -1, 0, 1, 0 };
        final int[] dx = new int[] { 0, 1, 0, -1 };
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    continue;
                }
                int cnt = 0;
                visited[i][j] = true;
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[] { i, j });
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    cnt++;
                    for (int d = 0; d < 4; d++) {
                        int ny = cur[0] + dy[d];
                        int nx = cur[1] + dx[d];

                        if (ny >= N || ny < 0 || nx >= N || nx < 0 || visited[ny][nx]) {
                            continue;
                        }

                        visited[ny][nx] = true;
                        queue.offer(new int[] { ny, nx });
                    }
                }
                pq.offer(cnt);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(pq.size()).append("\n");
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }
        sb.setLength(sb.length() - 1);
        System.out.println(sb);
    }
}