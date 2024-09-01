import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> m = new HashMap<>();
        int N = friends.length;
        for(int i=0; i<N; i++) {
            m.put(friends[i], i);
        }
        
        // 선물을 주고받은 관계
        int[][] a = new int[N][N];
        // 선물을 준 갯수
        int[] out = new int[N]; 
        // 선물을 받은 갯수
        int[] in = new int[N];
        for(int i=0; i<gifts.length; i++) {
            StringTokenizer stk = new StringTokenizer(gifts[i]);
            int gId = m.get(stk.nextToken());
            int rId = m.get(stk.nextToken());
            a[gId][rId]++;
            out[gId]++;
            in[rId]++;
        }
        
        int[] ret = new int[N];
        int answer = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(a[i][j] != a[j][i]) {
                    int r = a[i][j] > a[j][i] ? i : j;
                    answer = Math.max(answer, ++ret[r]);
                    continue;
                }
                
                int i_diff = out[i]-in[i];
                int j_diff = out[j]-in[j];
                
                if(i_diff != j_diff) {
                    int r = i_diff > j_diff ? i : j;
                    answer = Math.max(answer, ++ret[r]);
                }
            }
        }
        
        return answer;
    }
}