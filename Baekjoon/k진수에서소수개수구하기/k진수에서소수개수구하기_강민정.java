class k진수에서소수개수구하기_강민정 {
    public int solution(int n, int k) {
        int answer = 0;		// 조건에 맞는 소수의 개수
        int x = 0;		// 조건에 맞는 소수가 어디까지 만들어졌는지 알 수 있음
        String transN = Integer.toString(n, k);		// n을 k진수로 변환
        
        for (int start = 0; start < transN.length(); start = x) {		// 슬라이싱 시작점
            for (int end = start + 1; end <= transN.length(); end++) {	// substring에서 end-1 되니까 end <= transN.length()다.
                if (end == transN.length() || transN.charAt(end) == '0') {
                    if (isPrime(Long.parseLong(transN.substring(start, end)))) {	// start에서 end-1까지 문자열을 자르고 소수 판별
                        answer++;
                    }
                    x = end + 1;	// 조건에 맞는 소수를 찾은 후 시작점을 x 다음으로 건너띄기 위해서
                    break;
                }
            }
        }
        
        return answer;
    }

    /*
    	소수 판별 메서드
    */
    public boolean isPrime(long num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
