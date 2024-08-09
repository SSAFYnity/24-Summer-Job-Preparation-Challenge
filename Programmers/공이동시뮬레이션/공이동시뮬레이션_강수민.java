class Solution {
    private final int[] dx = {0, 0, 1, -1}; // 역순 탐색을 위해 쿼리의 반대 방향
    private final int[] dy = {1, -1, 0, 0}; // 우 좌 하 상

    public long solution(int n, int m, int x, int y, int[][] queries) {

        int sr = x; // 시작 범위 행 최대
        int er = x; // 시작 범위 행 최소
        int sc = y; // 시작 범위 열 최대
        int ec = y; // 시작 범위 열 최소

        for(int i = queries.length - 1; i >= 0; i--) { // 쿼리 역순 탐색
            int d = queries[i][0];
            int dist = queries[i][1];
            // 행이동 계산
            if(d >= 2) {
                int[] result = calRange(sr, er, dx[d]*dist, n);
                if(result[0] == -1) return 0; // 불가능
                sr = result[0];
                er = result[1];
            }
            // 열이동 계산
            else {
                int[] result = calRange(sc, ec, dy[d]*dist, m);
                if(result[0] == -1) return 0; // 불가능
                sc = result[0];
                ec = result[1];
            }
        }

        return (long)(er-sr+1) * (ec-sc+1);
    }

    // 범위 계산 : 해당 범위에서 제한 된 만큼 이동 한 범위
    private int[] calRange(int start, int end, int dist, int max) {

        // 다음 범위의 시작과 끝이 경계에 걸치는지 확인
        int nextS = (start == 0 && dist > 0) ? 0 : start + dist;
        int nextE = (end == max-1 && dist < 0) ? max - 1 : end + dist;

        // 1) 모두 벗어남
        if((nextS >= max || nextS < 0) && (nextE >= max || nextE < 0))
            return new int[] { -1, -1 };
            // 2) 시작만 벗어남
        else if(nextS < 0 && nextE >= 0 && nextE < max) {
            return new int[] { 0, nextE };
        }
        // 3) 끝만 벗어남
        else if(nextE >= max && nextS >= 0 && nextS < max) {
            return new int[] { nextS, max - 1 };
        }
        // 모두 가능
        else {
            return new int[] { nextS, nextE };
        }
    }
}