import java.util.*;

class Solution {
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        
        long left = (long) y, right = (long) y;
        long up = (long) x, down = (long) x;
        
        for (int q = queries.length - 1; q >= 0; q--) {
            int[] query = queries[q];
            int command = query[0];
            int dx = query[1];
            int[] dr = {0, 0};
            boolean isCorner = false;
            
            switch (command) {
                case 0 : dr[1]++; isCorner = left == 0; break;
                case 1 : dr[1]--; isCorner = right == m-1; break;
                case 2 : dr[0]++; isCorner = up == 0; break;
                default: dr[0]--; isCorner = down == n-1; 
            }
            
            if (isCorner) {
                if (dr[1] != 0) {
                    if (dr[1] == 1) right = Math.min(right + dr[1] * dx, m-1);
                    else left = Math.max(left + dr[1] * dx, 0);
                } else {
                    if (dr[0] == 1) down = Math.min(down + dr[0] * dx, n-1);
                    else up = Math.max(up + dr[0] * dx, 0);
                }
            } else {
                if (dr[1] != 0) {
                    left = Math.max(left + dr[1] * dx, 0);
                    right = Math.min(right + dr[1] * dx, m-1);
                } else {
                    up = Math.max(up + dr[0] * dx, 0);
                    down = Math.min(down + dr[0] * dx, n-1);
                }
            }
            if (right < 0 || left >= m || down < 0 || up >= n) return 0;
        }
        
        return (right - left + 1) * (down - up + 1);
    }
}