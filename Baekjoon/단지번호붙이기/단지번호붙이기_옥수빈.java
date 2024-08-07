import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                if (s.charAt(j) == '1') {
                    map[i][j] = s.charAt(j) - '0';
                }
            }
        }
        List<Integer> countList = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 1 && !visited[y][x]) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(x, y));
                    visited[y][x] = true;
                    int cnt = 0;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        cnt += size;
                        for (int i = 0; i < size; i++) {
                            Point p = queue.poll();
                            for (int d = 0; d < 4; d++) {
                                int nx = dx[d] + p.x;
                                int ny = dy[d] + p.y;
                                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                                    if (map[ny][nx] == 1 && !visited[ny][nx]) {
                                        visited[ny][nx] = true;
                                        queue.add(new Point(nx, ny));
                                    }
                                }
                            }
                        }
                    }
                    countList.add(cnt);
                }
            }
        }
        Collections.sort(countList);
        System.out.println(countList.size());
        for (int c : countList) {
            System.out.println(c);
        }
    }
}