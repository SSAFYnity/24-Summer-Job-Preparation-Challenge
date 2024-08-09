import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = chars[j] == '1';
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    result.add(dfs(i, j));
                }
            }
        }

        Collections.sort(result);

        sb.append(result.size()).append('\n');

        for (int i : result) {
            sb.append(i).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static int dfs(int x, int y) {
        map[x][y] = false;
        int sum = 1;
        for (int i = 0; i < 4; i++) {
            int row = x + dx[i];
            int col = y + dy[i];
            if (0 <= row && row < map.length && 0 <= col && col < map.length && map[row][col]) {
                sum += dfs(row, col);
            }
        }
        return sum;
    }
}