import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k, o, answer = -1;
    static int[][] office, mix, temperature, aircon;
    static boolean[][] upwall, leftwall;
    static class Spread {
        int y, x, c;
        public Spread(int y, int x, int c) {
            this.y = y;
            this.x = x;
            this.c = c;
        }
    }

    public static void airconOn() {
        ArrayDeque<Spread> que = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                switch (office[i][j]) {
                    case 0:
                    case 1: break;
                    case 2: { // 좌
                        que.clear();
                        visit = new boolean[n][n];
                        Spread init = new Spread(i, j-1, 5);
                        que.add(init);
                        while (!que.isEmpty()) {
                            Spread now = que.poll();
                            if (!visit[now.y][now.x]) {
                                aircon[now.y][now.x] += now.c;
                                visit[now.y][now.x] = true;
                            }
                            if (now.x == 0 || now.c == 1) continue;
                            if (now.y > 0 && !leftwall[now.y-1][now.x] && !upwall[now.y][now.x]) {
                                que.add(new Spread(now.y-1, now.x-1, now.c-1));
                            }
                            if (!leftwall[now.y][now.x]) que.add(new Spread(now.y, now.x-1, now.c-1));
                            if (now.y < n-1 && !leftwall[now.y+1][now.x] && !upwall[now.y+1][now.x]) {
                                que.add(new Spread(now.y+1, now.x-1, now.c-1));
                            }
                        }
                        break;
                    }
                    case 3: { // 상
                        que.clear();
                        visit = new boolean[n][n];
                        Spread init = new Spread(i-1, j, 5);
                        que.add(init);
                        while (!que.isEmpty()) {
                            Spread now = que.poll();
                            if (!visit[now.y][now.x]) {
                                aircon[now.y][now.x] += now.c;
                                visit[now.y][now.x] = true;
                            }
                            if (now.y==0 || now.c == 1) continue;
                            if (now.x > 0 && !leftwall[now.y][now.x] && !upwall[now.y][now.x-1]) {
                                que.add(new Spread(now.y-1, now.x-1, now.c-1));
                            }
                            if (!upwall[now.y][now.x]) que.add(new Spread(now.y-1, now.x, now.c-1));
                            if (now.x < n-1 && !leftwall[now.y][now.x+1] && !upwall[now.y][now.x+1]) {
                                que.add(new Spread(now.y-1, now.x+1, now.c-1));
                            }
                        }
                        break;
                    } 
                    case 4: { // 우
                        que.clear();
                        visit = new boolean[n][n];
                        Spread init = new Spread(i, j+1, 5);
                        que.add(init);
                        while (!que.isEmpty()) {
                            Spread now = que.poll();
                            if (!visit[now.y][now.x]) {
                                aircon[now.y][now.x] += now.c;
                                visit[now.y][now.x] = true;
                            }
                            if (now.x==n-1 || now.c == 1) continue;
                            if (now.y > 0 && !leftwall[now.y-1][now.x+1] && !upwall[now.y][now.x]) {
                                que.add(new Spread(now.y-1, now.x+1, now.c-1));
                            }
                            if (!leftwall[now.y][now.x+1]) que.add(new Spread(now.y, now.x+1, now.c-1));
                            if (now.y < n-1 && !leftwall[now.y+1][now.x+1] && !upwall[now.y+1][now.x]) {
                                que.add(new Spread(now.y+1, now.x+1, now.c-1));
                            }
                        }
                        break;
                    }
                    case 5: { // 하
                        que.clear();
                        visit = new boolean[n][n];
                        Spread init = new Spread(i+1, j, 5);
                        que.add(init);
                        while (!que.isEmpty()) {
                            Spread now = que.poll();
                            if (!visit[now.y][now.x]) {
                                aircon[now.y][now.x] += now.c;
                                visit[now.y][now.x] = true;
                            }
                            if (now.y==n-1 || now.c == 1) continue;
                            if (now.x > 0 && !leftwall[now.y][now.x] && !upwall[now.y+1][now.x-1]) {
                                que.add(new Spread(now.y+1, now.x-1, now.c-1));
                            }
                            if (!upwall[now.y+1][now.x]) que.add(new Spread(now.y+1, now.x, now.c-1));
                            if (now.x < n-1 && !leftwall[now.y][now.x+1] && !upwall[now.y+1][now.x+1]) {
                                que.add(new Spread(now.y+1, now.x+1, now.c-1));
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public static boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= n;
    }

    // 시원함의 차이 / 4 만큼 옮겨감
    public static void mixCool() {
        int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
        mix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temperature[i][j] < 4) continue;
                for (int d = 0; d < 4; d++) {
                    int y = i + dr[0][d];
                    int x = j + dr[1][d];
                    if (isOut(y,x) || temperature[i][j] - temperature[y][x] < 4) continue;
                    if (i==y && leftwall[y][Math.max(j, x)]) continue;
                    if (j==x && upwall[Math.max(i,y)][j]) continue;
                    mix[y][x] += (temperature[i][j] - temperature[y][x]) / 4;
                    mix[i][j] -= (temperature[i][j] - temperature[y][x]) / 4;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temperature[i][j] += mix[i][j];
                if ((i == 0 || i == n-1 || j == 0 || j == n-1) && temperature[i][j] > 0) temperature[i][j]--;
            }
        }
    }

    // 모든 사무실 공간(office[][] == 1)이 k보다 큰지
    public static boolean checkAllCool() {
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if (temperature[i][j] >= k && office[i][j] == 1) temp++;
            }
        }
        if (temp == o) return true;
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        office = new int[n][n];
        temperature = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] == 1) o++;
            }
        }

        upwall = new boolean[n][n];
        leftwall = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            if (w == 0) upwall[y][x] = true;
            else leftwall[y][x] = true;
        }

        int time = 0;
        if (k == 0 || o == 0) {
            time = 101;
            answer = 0;
        }

        aircon = new int[n][n];
        airconOn();

        while (time++ <= 100) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    temperature[i][j] += aircon[i][j];
                }
            }
            mixCool();
            if (checkAllCool()) {
                answer = time; 
                break;
            }
        }

        System.out.println(answer);
    }
}