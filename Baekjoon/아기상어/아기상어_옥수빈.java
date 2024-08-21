import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int sharkSize = 2;
        int sharkEat = 0;
        int sharkX = 0, sharkY = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0;
                }
            }
        }
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        int time = 0;
        while (true) {
            int[][] dist = new int[N][N];
            int minDist = Integer.MAX_VALUE;
            int minX = 0, minY = 0;
            boolean isFind = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = -1;
                }
            }
            dist[sharkX][sharkY] = 0;
            int[] queueX = new int[N * N];
            int[] queueY = new int[N * N];
            int front = 0, rear = 0;
            queueX[rear] = sharkX;
            queueY[rear++] = sharkY;
            while (front < rear) {
                int x = queueX[front];
                int y = queueY[front++];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (dist[nx][ny] != -1) continue;
                    if (map[nx][ny] > sharkSize) continue;
                    dist[nx][ny] = dist[x][y] + 1;
                    if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                        if (minDist > dist[nx][ny]) {
                            minDist = dist[nx][ny];
                            minX = nx;
                            minY = ny;
                            isFind = true;
                        } else if (minDist == dist[nx][ny]) {
                            if (minX == nx) {
                                if (minY > ny) {
                                    minX = nx;
                                    minY = ny;
                                }
                            } else if (minX > nx) {
                                minX = nx;
                                minY = ny;
                            }
                        }
                    }
                    queueX[rear] = nx;
                    queueY[rear++] = ny;
                }
            }
            if (!isFind) break;
            time += minDist;
            sharkX = minX;
            sharkY = minY;
            map[minX][minY] = 0;
            sharkEat++;
            if (sharkEat == sharkSize) {
                sharkSize++;
                sharkEat = 0;
            }

        }
        System.out.println(time);
    }
}