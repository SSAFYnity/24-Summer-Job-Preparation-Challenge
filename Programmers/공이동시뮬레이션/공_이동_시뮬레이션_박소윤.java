class Solution {
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long x1 = y; // 왼쪽 벽
        long y1 = x; // 위쪽 벽
        long x2 = y; // 오른쪽 벽
        long y2 = x; // 아래쪽 벽
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            int delta = queries[i][1];
            switch(command) {
                case 0 -> {
                    if (x1 != 0) {
                        x1 += delta;
                    }
                    x2 = Math.min(m - 1, x2 + delta);
                }
                case 1 -> {
                    if (x2 != m - 1) {
                        x2 -= delta;
                    }
                    x1 = Math.max(0, x1 - delta);
                }
                case 2 -> {
                    if (y1 != 0) {
                        y1 += delta;
                    }               
                    y2 = Math.min(n - 1, y2 + delta);
                }
                case 3 -> {
                    if (y2 != n - 1) {
                        y2 -= delta;
                    }
                    y1 = Math.max(0, y1 - delta);
                }
            }
            if (x2 < 0 || x1 > m - 1 || y2 < 0 || y1 > n - 1) {
               return 0;
            }
        }
        
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
}