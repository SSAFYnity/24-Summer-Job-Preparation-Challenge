import java.util.ArrayList;

public class 수레움직이기_이진곤 {
    class Solution {
        final int[] DY = new int[] { -1, 0, 1, 0 };
        final int[] DX = new int[] { 0, 1, 0, -1 };
        int[][] map;
        int R, C;
        int[] redStart, redEnd, blueStart, blueEnd;

        ArrayList<ArrayList<int[]>> redPath = new ArrayList<>();
        ArrayList<ArrayList<int[]>> bluePath = new ArrayList<>();

        public int solution(int[][] maze) {
            map = maze;
            setup();

            ArrayList<int[]> red = new ArrayList<>();
            boolean[][] visited = new boolean[R][C];
            visited[redStart[0]][redStart[1]] = true;
            red.add(new int[] { redStart[0], redStart[1] });
            getPath(red, visited, true);

            ArrayList<int[]> blue = new ArrayList<>();
            visited = new boolean[R][C];
            visited[blueStart[0]][blueStart[1]] = true;
            blue.add(new int[] { blueStart[0], blueStart[1] });
            getPath(blue, visited, false);

            int answer = Integer.MAX_VALUE;
            for (ArrayList<int[]> redList : redPath) {
                for (ArrayList<int[]> blueList : bluePath) {
                    int cnt = Math.max(redList.size(), blueList.size()) - 1;
                    if (cnt >= answer) {
                        continue;
                    }
                    if (checkValidPath(redList, blueList)) {
                        answer = cnt;
                    }
                }
            }
            return answer == Integer.MAX_VALUE ? 0 : answer;
        }

        boolean checkValidPath(ArrayList<int[]> redList, ArrayList<int[]> blueList) {
            int minSize = Math.min(redList.size(), blueList.size());
            int maxSize = Math.max(redList.size(), blueList.size());
            for (int i = 1; i < minSize; i++) {
                int[] red = redList.get(i);
                int[] blue = blueList.get(i);
                // 겹침 판정
                if (red[0] == blue[0] && red[1] == blue[1]) {
                    return false;
                }
                // 교환 판정
                int[] prevRed = redList.get(i - 1);
                int[] prevBlue = blueList.get(i - 1);
                if (red[0] == prevBlue[0] && red[1] == prevBlue[1]
                    && blue[0] == prevRed[0] && blue[1] == prevRed[1]) {
                    return false;
                }
            }
            if (blueList.size() < redList.size()) {
                for (int i = minSize; i < maxSize; i++) {
                    int[] red = redList.get(i);
                    if (red[0] == blueEnd[0] && red[1] == blueEnd[1]) {
                        return false;
                    }
                }
            }
            else if (blueList.size() > redList.size()) {
                for (int i = minSize; i < maxSize; i++) {
                    int[] blue = blueList.get(i);
                    if (blue[0] == redEnd[0] && blue[1] == redEnd[1]) {
                        return false;
                    }
                }
            }
            return true;
        }

        void getPath(ArrayList<int[]> path, boolean[][] visited, boolean isRed) {
            int[] cur = path.get(path.size() - 1);
            if (isRed && cur[0] == redEnd[0] && cur[1] == redEnd[1]) {
                redPath.add(path);
                return;
            }
            if (!isRed && cur[0] == blueEnd[0] && cur[1] == blueEnd[1]) {
                bluePath.add(path);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + DY[d];
                int nx = cur[1] + DX[d];

                if (ny >= R || ny < 0 || nx >= C || nx < 0 || visited[ny][nx] || map[ny][nx] == 5) {
                    continue;
                }

                visited[ny][nx] = true;
                path.add(new int[] { ny, nx });
                getPath(new ArrayList<>(path), visited, isRed);
                path.remove(path.size() - 1);
                visited[ny][nx] = false;
            }
        }

        void setup() {
            R = map.length;
            C = map[0].length;

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    switch (map[i][j]) {
                        case 1:
                            redStart = new int[] { i, j };
                            break;
                        case 2:
                            blueStart = new int[] { i, j };
                            break;
                        case 3:
                            redEnd = new int[] { i, j };
                            break;
                        case 4:
                            blueEnd = new int[] { i, j };
                            break;
                    }
                }
            }
        }
    }
}
