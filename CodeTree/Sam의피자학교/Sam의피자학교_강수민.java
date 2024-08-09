import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 밀가루 배열 크기
        int k = Integer.parseInt(st.nextToken()); // 최댓값과 최솟값의 차이
        int[] flour = new int[n]; // 밀가루 배열

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            flour[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;

        while(true) {
            // 최댓값, 최솟값 찾기
            int min = 3001;
            int max = 0;
            for(int i : flour) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }

            // 최댓값과 최솟값의 차이가 K 이하면 종료
            if(max - min <= k) break;

            // 1) 가장 작은 밀가루 위치에 +1
            for(int i = 0; i < n; i++) {
                if(flour[i] == min) flour[i]++;
            }

            // 2) 도우 말기 + 3) 도우 누르기
            flour = press(rolling(flour));

            // 4) 도우 두 번 접기 + 5) 도우 누르기
            flour = press(fold(flour));

            cnt++;
        }

        // Output
        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    private static int[][] rolling(int[] flour) { // 도우 말기

        int[][] dough = new int [1][n];
        for(int i = 0; i < n; i++) {
            dough[0][i] = flour[i];
        }

        int idx = 1; // 다음 자를 위치(이 인덱스 전까지)
        int hRow = 1; // 겹친 부분 높이 (배열의 첫 원소는 이미 겹쳐있다고 보기)
        int hCol = 1; // 겹친 부분 너비

        while(hRow <= n - idx) { // 도우 말 수 있으면 진행

            if(hRow == hCol) hRow++;
            else hCol++;

            // 도우의 다음 말린 형태
            int[][] next = new int[hRow][n - idx];

            // 도우 바닥 값 복사
            for(int i = idx; i < n; i++) {
                next[hRow-1][i-idx] = flour[i];
            }

            // 도우 머리 값 복사
            for(int i = 0; i < hRow - 1; i++) {
                for(int j = 0; j < hCol; j++) {
                    next[i][j] = dough[dough.length-1 - j][i];
                }
            }
            dough = next;
            idx += hCol;
        }

        return dough;
    }

    private static final int[] dx = {0, 1}; // 우 하
    private static final int[] dy = {1, 0};

    private static int[] press(int[][] dough) { // 도우 누르기
        int row = dough.length;
        int col = dough[0].length;
        int[][] copy = new int[row][col];

        for(int i = 0; i < row; i++) { // 2차원배열 깊은 복사
            for(int j = 0; j < col; j++) {
                copy[i][j] = dough[i][j];
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(dough[i][j] == 0) continue;
                for(int k = 0; k < 2; k++) { // 우, 하 방향만 확인

                    int r = i + dx[k];
                    int c = j + dy[k];

                    if(0 <= r && r < row && 0 <= c && c < col && dough[r][c] > 0) {

                        int d = Math.abs(dough[i][j] - dough[r][c]) / 5;

                        if(dough[i][j] > dough[r][c]) {
                            copy[i][j] -= d;
                            copy[r][c] += d;
                        } else {
                            copy[i][j] += d;
                            copy[r][c] -= d;
                        }
                    }
                }
            }
        }

        int idx = 0;
        int[] result = new int[n];

        for(int i = 0; i < col; i++) {
            for(int j = row-1; j >= 0; j--) {
                if (copy[j][i] > 0) {
                    result[idx++] = copy[j][i];
                }
            }
        }

        return result;
    }

    private static int[][] fold(int[] flour) {  // 도우 두 번 접기
        int[][] temp = new int [2][n/2];

        // 첫 번째 반 접기
        // 위층
        for(int i = 0; i < n/2; i++) {
            temp[0][i] = flour[n/2-1 - i];
        }
        // 아래층
        for(int i = 0; i < n/2; i++) {
            temp[1][i] = flour[n/2 + i];
        }

        int[][] dough = new int [4][n/4];

        // 두 번째 반 접기
        // 위층
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < n/4; j++) {
                dough[i][j] = temp[temp.length-1 - i][n/4-1 - j];
            }
        }
        // 아래층
        for(int i = 2; i < 4; i++) {
            for(int j = 0; j < n/4; j++) {
                dough[i][j] = temp[i - 2][j + n/4];
            }
        }

        return dough;
    }
}