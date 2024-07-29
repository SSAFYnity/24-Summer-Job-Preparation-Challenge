class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int startTime = 3600*h1+60*m1+s1;
        int endTime = 3600*h2+60*m2+s2;
        
        // 시침 : 1분당 1번씩 겹치고 720분 중 1분은 안겹침
        // 분침 : 1분당 1번씩 겹치고 60분 중 1분은 안겹침
        int count1 = 2*(60*h1+m1)-2 - startTime/43200 - startTime/3600;
        int count2 = 2*(60*h2+m2)-2 - endTime/43200 - endTime/3600;
        
        // 남은 초가 시/분침과 겹치는 지 판단
        double startH = ((h1%12 / (double)12) + (m1/(double)720) + (s1/(double)43200))*60;
        double startM = ((m1 / (double)60) + (s1/(double)3600))*60;
        if(startH<=s1) {count1++;}
        if(startM<=s1) {count1++;}
        
        // 남은 초가 시/분침과 겹치는 지 판단
        startH = ((h2%12 / (double)12) + (m2/(double)720) + (s2/(double)43200))*60;
        startM = ((m2 / (double)60) + (s2/(double)3600))*60;
        if(startH<=s2) {count2++;}
        if(startM<=s2) {count2++;}
        
        // 12시 시침 분침 겹치는 시간 체크
        int check12 = 12*3600;
        if(startTime<check12 && check12<=endTime){count2--;}
        // 시작시간이 12시 인경우 count++
        if(startTime==0 || startTime==check12){count2++;}
        
        return count2-count1;
    }
}