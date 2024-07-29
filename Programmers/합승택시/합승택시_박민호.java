import java.util.*;
class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int S = s-1;
        int A = a-1;
        int B = b-1;
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(map[i], 100000*200);
            map[i][i] = 0;
        }
        for(int i=0;i<fares.length;i++){
            map[fares[i][0]-1][fares[i][1]-1] = fares[i][2];
            map[fares[i][1]-1][fares[i][0]-1] = fares[i][2];
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        int answer = map[S][A] + map[S][B];
        for(int k=0;k<n;k++){
            answer = Math.min(answer, map[S][k] + map[k][A] + map[k][B]);
        }
        return answer;
    }
}