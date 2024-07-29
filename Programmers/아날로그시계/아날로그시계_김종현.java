
class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        int sh = h1 * 60 * 60;
        int sm = m1 * 60;
        int ss = s1;
        int startTime = (sh+sm+ss);
        
        int eh = h2 * 60 * 60;
        int em = m2 * 60;
        int es = s2;
        int endTime = (eh+em+es);
        
        int ret = check(startTime);
        
        return go(endTime) - go(startTime)+ret;
    }
    
    private int go(int time) {
        int p = 43200 <= time ? 2 : 1;
        int h = time * 719 / 43200;
        int m = time * 59 / 3600;
        return h+m - p;
    }
    
    private int check(int time) {
        if(time * 719 % 43200 ==0 || time * 59 % 3600 == 0) {
            return 1;
        }
        return 0;
    }
}