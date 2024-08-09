class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        int startTime = h1 * 3600 + m1 * 60 + s1; // 시작 시간의 초
        int endTime = h2 * 3600 + m2 * 60 + s2; // 종료 시간의 초

        int answer = 0; // 초침이 시,분침과 겹친 횟수

        // 시작 시간이 12시(정오 or 자정)인 경우 +1
        if (startTime % 360 == 0 || startTime % 360 == 12)
            answer++;

        /*
            <종료 시간이 될 때까지 시작 시간 1초씩 증가>
            - 시침 : 360도-12시간 >> 1시간-30도 >> 1분-30/60 = 1/2 >> 1초 1/120
            - 분침 : 360도-60분 >> 1분-6도 >> 1초-1/10도
            - 초침 : 360도-60초 >> 1초-6도
            
        */
        for(; startTime < endTime; startTime++) {

            // 현재 시간의 침들의 위치
            double nowH = startTime / 120.0 % 360;
            double nowM = startTime / 10.0 % 360;
            double nowS = startTime * 6 % 360;

            // 1초 후 침들의 위치
            double nextH = (startTime + 1) / 120.0 % 360;
            double nextM = (startTime + 1) / 10.0 % 360;
            double nextS = (startTime + 1) * 6 % 360;

            // 0도는 360도로 생각하기
            if(nextH == 0) nextH = 360;
            if(nextM == 0) nextM = 360;
            if(nextS == 0) nextS = 360;

            // 초침이 시침 넘어섬 or 겹침
            if(nowS < nowH && nextS >= nextH) answer++;
            // 초침이 분침 넘어섬 or 겹침
            if(nowS < nowM && nextS >= nextM) answer++;

            // 시침, 분침이 겹친 경우 -1
            if(nextH == nextM) answer--;
        }

        return answer;
    }
}