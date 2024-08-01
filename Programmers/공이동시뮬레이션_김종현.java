class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;
        
        long r1 = x, r2 = x;
        long c1 = y, c2 = y; 
        
        for(int i=queries.length-1; i>=0; i--){
            int dir = queries[i][0];
            int cnt = queries[i][1];
            
            // l -> r
            if(dir==0){ 
                if(c1!=0) c1 += cnt;
                c2 = Math.min(c2+cnt, m-1);
                if(c1>m-1) return 0;
            }
            else if(dir==1) {
                // r -> l
                if(c2!=m-1) c2 -= cnt;
                c1 = Math.max(c1-cnt, 0);
                if(c2<0) return 0;
            }
            else if(dir==2){
                // u -> d
                if(r1!=0) r1 += cnt;
                r2 = Math.min(r2+cnt, n-1);
                if(r1>n-1) return 0;
            }
            else{
                // d -> u
                if(r2!=n-1) r2 -= cnt;
                r1 = Math.max(r1-cnt, 0);
                if(r2<0) return 0;
            }
        }
        
        return (r2-r1+1)*(c2-c1+1);
    }
}