import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String[] sps = go(n,k).split("0");
        outer:for(int i=0; i<sps.length; i++) {
            if(sps[i].equals("") || sps[i].equals("1")) continue;
            long num = Long.parseLong(sps[i]);
            for(int j=2; j<=Math.sqrt(num); j++) {
                if(num%j == 0) continue outer;
            }
            answer++;
        }
        return answer;
    }
    
    private static String go(int n, int k) {
        if(n/k==0) return n%k+"";
        return go(n/k,k) + n%k+"";
    }
}
