import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 단지번호붙이기_신유진 {
    static int N;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static int[][] map;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 1) {
                    map[y][x] = 2;
                    int count = dfs(x, y);
                    answer.add(count);
                }
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (int count : answer) {
            System.out.println(count);
        }
    }

    private static int dfs(int x, int y) {
        int count = 1;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[ny][nx] != 1)
                continue;

            map[ny][nx] = 2;
            count += dfs(nx, ny);
        }
        return count;
    }
}
