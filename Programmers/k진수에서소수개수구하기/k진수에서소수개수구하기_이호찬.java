import java.util.*;

class Solution {
	private boolean isPrime(long num) {
		if (num == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public int solution(int n, int k) {
		String strNum = Integer.toString(n, k); // k진수 변환
		StringTokenizer st = new StringTokenizer(strNum, "0");
		int answer = 0;

		while (st.hasMoreTokens()) {
			if (isPrime(Long.parseLong(st.nextToken()))) {
				answer++;
			}
		}

		return answer;
	}
}