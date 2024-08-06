import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<Integer> ans;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N][N];
        ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 || visit[i][j]) continue;
                bfs(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append("\n");
        Collections.sort(ans);
        for (int i : ans) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int i, int j) {
        visit[i][j] = true;
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(i, j));
        int count = 0;
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            count++;

            for (int k = 0; k < 4; k++) {
                int mx = cur.x + dx[k];
                int my = cur.y + dy[k];
                if (mx < 0 || mx >= N || my < 0 || my >= N) continue;
                if (visit[mx][my] || map[mx][my] == 0) continue;
                visit[mx][my] = true;
                q.offer(new Pos(mx, my));
            }
        }
        ans.add(count);
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}