import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 냉방시스템_이진곤 {
    static int n, k;
    static int[][][] map;
    static boolean[][] visited;
    static ArrayList<int[]> visitedTemp = new ArrayList<>();
    static ArrayList<int[]> officeList = new ArrayList<>();
    static ArrayList<int[]> airconList = new ArrayList<>();
    static final int[][] dy = new int[][] {
            new int[] { -1, 0, 1 }, new int[] { -1, -1, -1 }, new int[] { -1, 0, 1 }, new int[] { 1, 1, 1 }
    };
    static final int[][] dx = new int[][] {
            new int[] { -1, -1, -1 }, new int[] { -1, 0, 1 }, new int[] { 1, 1, 1 }, new int[] { -1, 0, 1 }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n][2];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    officeList.add(new int[] { i, j });
                }
                else if (num > 0) {
                    airconList.add(new int[] { i, j, num - 2 });
                }
                // 에어컨 - 0 : 왼쪽, 1 : 위, 2 : 오른쪽, 3 : 아래
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            if (s == 0) { // 위에 벽
                map[y][x][1] |= (1 << 1);
                map[y - 1][x][1] |= (1 << 3);
            }
            else { // 왼쪽 벽
                map[y][x][1] |= 1;
                map[y][x - 1][1] |= (1 << 2);
            }
        }

        int time = 0;
        while (true) {
            time++;
            // 1. 에어컨에서 바람이 나온다
            for (int[] aircon : airconList) {
                blowWind(aircon[0] + dy[aircon[2]][1], aircon[1] + dx[aircon[2]][1], aircon[2], 5);
                for (int[] arr : visitedTemp) {
                    visited[arr[0]][arr[1]] = false;
                }
            }
            // 2. 공기들이 섞인다
            int[][] divideMap = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    divideWind(divideMap, i, j);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j][0] += divideMap[i][j];
                }
            }
            // 3. 외벽 칸의 시원함이 감소한다.
            outerHot();
            boolean isCool = true;
            for (int[] office : officeList) {
                if (map[office[0]][office[1]][0] < k) {
                    isCool = false;
                    break;
                }
            }
            if (isCool || time >= 100) {
                break;
            }
        }
        System.out.println(time >= 100 ? -1 : time);
    }

    static void print(int type) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j][type] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void blowWind(int y, int x, int dir, int power) {
        map[y][x][0] += power;
        if (power == 1) {
            return;
        }

        for (int d = 0; d < 3; d++) {
            int ny = y + dy[dir][d];
            int nx = x + dx[dir][d];

            if (ny >= n || ny < 0 || nx >= n || nx < 0 || visited[ny][nx]) {
                continue;
            }

            // 벽 판정 넣기
            //ny nx의 오른쪽이 막히면 X => (1 << 2), (1 << 3)보다 초과하면 (>> 4)
            int wall = (1 << dir) << 2;
            if (wall > (1 << 3)) {
                wall >>= 4;
            }

            if ((map[ny][nx][1] & wall) >= 1) {
                continue;
            }

            // 왼쪽 -> 0은 현재의 아래, 2는 위가 막히면 X, 오른쪽 -> 0은 아래, 2는 위
            // 위 -> 0은 왼쪽 2는 오른쪽, 아래 -> 0은 왼쪽, 2는 오른쪽
            if (dir % 2 == 0) {
                if (d == 0) { // 위 벽 체크
                    if ((map[y][x][1] & (1 << 1)) >= 1) {
                        continue;
                    }
                }
                else if (d == 2) { // 아래 벽 체크
                    if ((map[y][x][1] & (1 << 3)) >= 1) {
                        continue;
                    }
                }
            }
            else {
                if (d == 0) { // 왼 벽 체크
                    if ((map[y][x][1] & (1 << 0)) >= 1) {
                        continue;
                    }
                }
                else if (d == 2) { // 오른 벽 체크
                    if ((map[y][x][1] & (1 << 2)) >= 1) {
                        continue;
                    }
                }
            }

            visited[ny][nx] = true;
            visitedTemp.add(new int[] { ny, nx });
            blowWind(ny, nx, dir, power - 1);
        }
    }

    static void divideWind(int[][] divideMap, int y, int x) {
        // 왼쪽
        if (x > 0 && (map[y][x][1] & (1 << 0)) == 0 && map[y][x][0] > map[y][x-1][0]) {
            int gap = (map[y][x][0] - map[y][x-1][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y][x-1] += gap;
        }

        // 위
        if (y > 0 && (map[y][x][1] & (1 << 1)) == 0 && map[y][x][0] > map[y-1][x][0]) {
            int gap = (map[y][x][0] - map[y-1][x][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y-1][x] += gap;
        }

        // 오른쪽
        if (x < n - 1 && (map[y][x][1] & (1 << 2)) == 0 && map[y][x][0] > map[y][x+1][0]) {
            int gap = (map[y][x][0] - map[y][x+1][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y][x+1] += gap;
        }

        // 아래
        if (y < n - 1 && (map[y][x][1] & (1 << 3)) == 0 && map[y][x][0] > map[y+1][x][0]) {
            int gap = (map[y][x][0] - map[y+1][x][0]) / 4;
            divideMap[y][x] -= gap;
            divideMap[y+1][x] += gap;
        }
    }

    static void outerHot() {
        for (int y = 0; y < n; y++) {
            if (map[y][0][0] > 0) {
                map[y][0][0] -= 1;
            }
            if (map[y][n - 1][0] > 0) {
                map[y][n - 1][0] -= 1;
            }
        }
        for (int x = 1; x < n - 1; x++) {
            if (map[0][x][0] > 0) {
                map[0][x][0] -= 1;
            }
            if (map[n - 1][x][0] > 0) {
                map[n - 1][x][0] -= 1;
            }
        }
    }
}