import java.util.*;
import java.io.*;


public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static boolean[][] map;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    public static void input() throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] cArr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                if (cArr[j] == '1') map[i][j] = true;
            }
        }
    }

    public static void pro() {

        ArrayList<Integer> cntHouseList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    cntHouseList.add(bfs(new int[] {i, j}));
                }
            }
        }

        Collections.sort(cntHouseList);
        sb.append(cntHouseList.size()).append('\n');
        for (Integer integer : cntHouseList) {
            sb.append(integer).append('\n');
        }

        System.out.println(sb);
    }


    public static int bfs(int[] loc) {

        map[loc[0]][loc[1]] = false;
        Deque<int[]> que = new ArrayDeque<>();

        int cnt = 1;
        que.add(loc);

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                if (ny == -1 || nx == -1 || ny == n || nx == n) continue;
                if (!map[ny][nx]) continue;
                cnt++;
                map[ny][nx] = false;
                que.add(new int[] {ny, nx});
            }

        }
        return cnt;
    }

    public static void main(String args[]) throws IOException
    {
        input();
        pro();

    }
}