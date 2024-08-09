import java.io.*;
import java.util.*;

class Solution {
    static int N, M, ans;
    static boolean[][][] visit;
    static boolean redFinished, blueFinished;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        visit = new boolean[2][N][M];
        ans = Integer.MAX_VALUE;
        Pos curRed = null, curBlue = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    curRed = new Pos(i, j);
                } else if (maze[i][j] == 2) {
                    curBlue = new Pos(i, j);
                }
            }
        }

        visit[0][curRed.x][curRed.y] = true;
        visit[1][curBlue.x][curBlue.y] = true;

        dfs(maze, curRed, curBlue, 0);

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    private static void dfs(int[][] maze, Pos red, Pos blue, int cnt) {
        if (redFinished && blueFinished) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int k = 0; k < dx.length; k++) {
            for (int l = 0; l < dx.length; l++) {
                Pos nextRed, nextBlue;
                if (redFinished) {
                    nextRed = red;
                } else {
                    nextRed = new Pos(red.x + dx[k], red.y + dy[k]);
                }
                if (blueFinished) {
                    nextBlue = blue;
                } else {
                    nextBlue = new Pos(blue.x + dx[l], blue.y + dy[l]);
                }

                if (!isPossible(maze, red, blue, nextRed, nextBlue)) continue;
                if (maze[nextRed.x][nextRed.y] == 3) redFinished = true;
                if (maze[nextBlue.x][nextBlue.y] == 4) blueFinished = true;

                visit[0][nextRed.x][nextRed.y] = true;
                visit[1][nextBlue.x][nextBlue.y] = true;

                dfs(maze, nextRed, nextBlue, cnt + 1);

                visit[0][nextRed.x][nextRed.y] = false;
                visit[1][nextBlue.x][nextBlue.y] = false;
                redFinished = false;
                blueFinished = false;
            }
        }
    }

    static boolean isPossible(int[][] map, Pos curRed, Pos curBlue, Pos red, Pos blue) {
        // 범위 체크
        if (red.x < 0 || red.x >= N || red.y < 0 || red.y >= M || blue.x < 0 || blue.x >= N || blue.y < 0 || blue.y >= M)
            return false;
        // 벽
        if (map[red.x][red.y] == 5 || map[blue.x][blue.y] == 5) return false;

        // 재방문 체크
        if (!redFinished && visit[0][red.x][red.y] || !blueFinished && visit[1][blue.x][blue.y]) return false;

        // 동시에 두 수레가 같은 칸인지 체크
        if (red.x == blue.x && red.y == blue.y) return false;

        // 수레끼리 자리 바꾸기 체크
        if ((curRed.x == blue.x && curRed.y == blue.y) && (curBlue.x == red.x && curBlue.y == red.y)) return false;

        return true;
    }

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}