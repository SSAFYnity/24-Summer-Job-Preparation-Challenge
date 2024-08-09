import java.util.ArrayList;

class Solution {
    public String[] solution(int[][] line) {

        // 교점의 x, y 최대 최소 => 배열 크기에 사용
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        // 교점 리스트
        ArrayList<int[]> list = new ArrayList<>();

        // 교점 찾기
        for(int i = 0; i < line.length; i++) {
            for(int j = i + 1; j < line.length; j++) {
                long A1 = line[i][0], B1 = line[i][1], C1 = line[i][2];
                long A2 = line[j][0], B2 = line[j][1], C2 = line[j][2];

                long denominator = A1 * B2 - A2 * B1;
                if(denominator == 0) continue; // 두 직선이 평행 혹은 일치

                long bx = B1 * C2 - B2 * C1;
                long by = A2 * C1 - A1 * C2;

                if (bx % denominator != 0 || by % denominator != 0) continue; // 정수 교점만

                long x = bx / denominator;
                long y = by / denominator;

                int ix = (int) x;
                int iy = (int) y;

                list.add(new int[] { ix, iy });
                minX = Math.min(minX, ix);
                minY = Math.min(minY, iy);
                maxX = Math.max(maxX, ix);
                maxY = Math.max(maxY, iy);
            }
        }

        // 배열 채우기
        int col = maxX - minX + 1;
        int row = maxY - minY + 1;

        char[][] arr = new char[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                arr[i][j] = '.';
            }
        }

        for(int[] p : list) {
            arr[maxY - p[1]][p[0] - minX] = '*';
        }

        // 출력
        String[] answer = new String[row];

        for(int i = 0; i < row; i++) {
            answer[i] = String.valueOf(arr[i]);
        }

        return answer;
    }
}
