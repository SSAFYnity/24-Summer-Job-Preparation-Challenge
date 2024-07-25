class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        int answer = 0;
        int startSecond = h1*3600 + m1*60 + s1;
        int endSecond = h2*3600 + m2*60 + s2;

        double hourAnglePerSecond = (double)360/43200;
        double minuteAnglePerSecond = (double)360/3600;
        double secondAnglePerSecond = (double)360/60;

        if(startSecond == 0 || startSecond == 43200) answer++;

        for(int i = startSecond+1; i<=endSecond; i++){
            int flag = 0;
            double pastHourAngle = ((i-1) * hourAnglePerSecond) % 360.0;
            double pastMinuteAngle = ((i-1) * minuteAnglePerSecond) % 360.0;
            double pastSecondAngle = ((i-1) * secondAnglePerSecond) % 360.0;

            double currentHourAngle = (i * hourAnglePerSecond) % 360.0;
            double currentMinuteAngle = (i * minuteAnglePerSecond) % 360.0;
            double currentSecondAngle = (i * secondAnglePerSecond) % 360.0;

            if(currentHourAngle == 0) currentHourAngle = 360;
            if(currentMinuteAngle == 0) currentMinuteAngle = 360;
            if(currentSecondAngle == 0) currentSecondAngle = 360;

            if((pastSecondAngle < pastHourAngle) && (currentSecondAngle>=currentHourAngle)){
                answer++;
            }
            if((pastSecondAngle < pastMinuteAngle) && (currentSecondAngle>=currentMinuteAngle)){
                answer++;
            }
            if(currentHourAngle == currentMinuteAngle) answer--;
        }

        return answer;
    }
}
