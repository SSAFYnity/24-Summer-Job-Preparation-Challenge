import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        
        List<long[]> list= new ArrayList<>();
        for(int i=0; i<line.length; i++) {
            for(int j=i+1; j<line.length; j++) {
                long a = line[i][0];
                long b = line[i][1];
                long e = line[i][2];
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                
                long div = a*d - b*c;
                if(div != 0) {
                    double x = 1D * (b*f-e*d) / div;
                    double y = 1D * (c*e-a*f) / div;
                    if(x % 1 == 0 && y % 1 == 0) {
                        list.add(new long[] {(long)x, (long)y});
                    }
                }
            }
        }
        
        long[] min = new long[] {Long.MAX_VALUE, Long.MAX_VALUE};
        long[] max = new long[] {Long.MIN_VALUE, Long.MIN_VALUE};
        
        for(long[] curr : list) {
            min[0] = Math.min(min[0], curr[0]);
            min[1] = Math.min(min[1], curr[1]);
            max[0] = Math.max(max[0], curr[0]);
            max[1] = Math.max(max[1], curr[1]);
        }
        
        int r = (int)(max[1]-min[1]);
        int c = (int)(max[0]-min[0]);
        
        
        char[][] a = new char[r+1][c+1];
        
        for(int i=0; i<=r; i++) {
            Arrays.fill(a[i], '.');
        }
        
        for(long[] curr : list) {
            a[(int)(max[1] - curr[1])][(int)(curr[0]-min[0])] = '*';
        }
        
        String[] answer = new String[r+1];
        for(int i=0; i<=r; i++) {
            answer[i] = new String(a[i]);
        }
        
        return answer;
    }
}