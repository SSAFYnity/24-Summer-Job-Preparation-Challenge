import java.util.*;

class Solution {

    int[] dy = {0, 1, 0, -1};
    int[] dx = {1, 0, -1, 0};

    int[] curRed, curBlue;
    int[][] maze;
    boolean[][] visitRed, visitBlue;

    boolean isRedEscape, isBlueEscape;

    int n, m, answer = Integer.MAX_VALUE;
    int maxDepth;

    public int solution(int[][] maze) {

        n = maze.length;
        m = maze[0].length;
        visitRed = new boolean[n][m];
        visitBlue = new boolean[n][m];

        maxDepth = n * m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 0) continue;
                if (maze[i][j] == 1) {
                    visitRed[i][j] = true;
                    maze[i][j] = 0;
                    curRed = new int[] {i, j};
                    continue;
                }

                if (maze[i][j] == 2) {
                    visitBlue[i][j] = true;
                    maze[i][j] = 0;
                    curBlue = new int[] {i, j};
                    continue;
                }
            }
        }

        this.maze = maze.clone();

        dfs(0, curRed, curBlue);

        if (answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }

    public void dfs(int depth, int[] curRed, int[] curBlue) {

        if (depth > answer) return;
        if (depth == maxDepth) return;

        if (maze[curRed[0]][curRed[1]] == 3 && maze[curBlue[0]][curBlue[1]] == 4) {
            answer = Math.min(answer, depth);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = curRed[0];
            int nx = curRed[1];

            if (maze[curRed[0]][curRed[1]] != 3) {
                ny = curRed[0] + dy[i];
                nx = curRed[1] + dx[i];
                if (ny == -1 || nx == -1 || ny == n || nx == m) continue;
                if (visitRed[ny][nx]) continue;
                if (maze[ny][nx] == 5) continue;
                if (ny == curBlue[0] && nx == curBlue[1]) continue;
                visitRed[ny][nx] = true;
            }

            for (int j = 0; j < 4; j++) {
                int ny2 = curBlue[0];
                int nx2 = curBlue[1];

                if (maze[curBlue[0]][curBlue[1]] != 4) {
                    ny2 = curBlue[0] + dy[j];
                    nx2 = curBlue[1] + dx[j];
                    if (ny2 == -1 || nx2 == -1 || ny2 == n || nx2 == m) continue;
                    if (visitBlue[ny2][nx2]) continue;
                    if (maze[ny2][nx2] == 5) continue;
                    if (ny == ny2 && nx == nx2) continue;
                    visitBlue[ny2][nx2] = true;
                }

                dfs(depth + 1, new int[]{ny, nx}, new int[]{ny2, nx2});
                if (maze[curBlue[0]][curBlue[1]] != 4) visitBlue[ny2][nx2] = false;
            }
            if (maze[curRed[0]][curRed[1]] != 3) visitRed[ny][nx] = false;
        }

        for (int i = 0; i < 4; i++) {
            int ny = curBlue[0];
            int nx = curBlue[1];

            if (maze[curBlue[0]][curBlue[1]] != 4) {
                ny = curBlue[0] + dy[i];
                nx = curBlue[1] + dx[i];
                if (ny == -1 || nx == -1 || ny == n || nx == m) continue;
                if (visitBlue[ny][nx]) continue;
                if (maze[ny][nx] == 5) continue;
                if (ny == curRed[0] && nx == curRed[1]) continue;
                visitBlue[ny][nx] = true;
            }

            for (int j = 0; j < 4; j++) {
                int ny2 = curRed[0];
                int nx2 = curRed[1];

                if (maze[curRed[0]][curRed[1]] != 3) {
                    ny2 = curRed[0] + dy[j];
                    nx2 = curRed[1] + dx[j];
                    if (ny2 == -1 || nx2 == -1 || ny2 == n || nx2 == m) continue;
                    if (visitRed[ny2][nx2]) continue;
                    if (maze[ny2][nx2] == 5) continue;
                    if (ny == ny2 && nx == nx2) continue;

                    visitRed[ny2][nx2] = true;
                }
                dfs(depth + 1, new int[]{ny2, nx2}, new int[]{ny, nx});
                if (maze[curRed[0]][curRed[1]] != 3) visitRed[ny2][nx2] = false;
            }
            if (maze[curBlue[0]][curBlue[1]] != 4) visitBlue[ny][nx] = false;
        }
    }
}