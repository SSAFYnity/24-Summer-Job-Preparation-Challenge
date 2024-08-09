import java.util.*;

class Solution {
    
    static int n, m, answer;
    static int[] red, blue;
    static int[][] dr = {{0,1,0,-1}, {1,0,-1,0}};
    static boolean[][] rv, bv;
    static int[][] maze;
    
    class Sureh implements Comparable<Sureh> {
        int y, x, w, v, t;
        public Sureh(int y, int x, int w, int v, int t) {
            this.y = y;
            this.x = x;
            this.w = w;
            this.v = v;
            this.t = t;
        }
        public int compareTo(Sureh o) {
            return this.t - o.t;
        }
        public String toString() {
            return "red [" + y + ", " + x + "] blue [" + w + ", " + v + "] " + t;
        }
    }
    
    public boolean isOut(int y, int x) {
        return y < 0 || y >= n || x < 0 || x >= m;
    }
    
    public boolean isArrival(Sureh s) {
        return s.y == red[0] && s.x == red[1] && s.w == blue[0] && s.v == blue[1];
    }
    
    public void back(Sureh s) {
        if (s.t >= answer) return;
        if (isArrival(s)) {
            answer = Math.min(answer, s.t);
            return;
        }
        
        for (int d = 0; d < 4; d++) {
            int y, x, w, v;
            if (s.y != red[0] || s.x != red[1]) {
                y = s.y + dr[0][d];
                x = s.x + dr[1][d];
                if (isOut(y,x) || rv[y][x] || maze[y][x] == 5) continue;
            } else {
                y = s.y;
                x = s.x;
            }
            
            for (int r = 0; r < 4; r++) {
                if (s.w != blue[0] || s.v != blue[1]) {
                    w = s.w + dr[0][r];
                    v = s.v + dr[1][r];
                    if (isOut(w,v) || bv[w][v] || maze[w][v] == 5) continue;
                } else {
                    w = s.w;
                    v = s.v;
                }
                if ((y==w&&x==v) || (s.y==w&&s.x==v) && (y==s.w&&x==s.v)) continue;
                
                if (y != red[0] || s.x != red[1]) {
                    rv[y][x] = true;
                }
                if (w != blue[0] || s.v != blue[1]) {
                    bv[w][v] = true;
                }
                
                
                back(new Sureh(y,x,w,v,s.t+1));
                
                if (s.y != red[0] || s.x != red[1]) {
                    rv[y][x] = false;
                }
                if (s.w != blue[0] || s.v != blue[1]) {
                    bv[w][v] = false;
                }
            }
        }
    }
    
    public int solution(int[][] maze) {
        int inf = Integer.MAX_VALUE;
        answer = inf;
        
        n = maze.length;
        m = maze[0].length;
        
        rv = new boolean[n][m];
        bv = new boolean[n][m];
        
        int[] temp = new int[4];
        red = new int[2];
        blue = new int[2];
        
        Solution.maze = maze;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (maze[i][j]) {
                    case 1: {
                        temp[0] = i;
                        temp[1] = j;
                        rv[i][j] = true;
                        break;
                    }
                    case 2: {
                        temp[2] = i;
                        temp[3] = j;
                        bv[i][j] = true;
                        break;
                    }
                    case 3: {
                        red[0] = i;
                        red[1] = j;
                        break;
                    }
                    case 4: {
                        blue[0] = i;
                        blue[1] = j;
                        break;
                    }
                }
            }
        }
        
        
        back(new Sureh(temp[0], temp[1], temp[2], temp[3], 0));
        
        return answer == inf ? 0 : answer;
    }
}