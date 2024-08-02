class Solution {

	// 초침이 분침, 시침과 겹칠 때 알람이 울린다.
	// 두 개의 시각이 주어질 때 알람이 울리는 횟수 구하기

	private int toSecond(int h, int m, int s) {
		return h * 3600 + m * 60 + s;
	}

	private int calculate(int time) {
		int result = 0;
		// 3600초(60분) 동안 초침이 분침은 59번 만난다.
		result += time * 59 / 3600;
		// 12시간(720분) 동안 초침과 시침은 719번 만난다.
		result += time * 719 / 43200;
		// 12시 정각이 몇 번 나오는지 계산
		int sub = time >= 12 * 3600 ? 2 : 1;
		return result - sub;
	}

	private boolean alarmNow(int time) {
		return ((time * 59 % 3600 == 0) || (time * 719 % 43200 == 0));
	}

	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int startTime = toSecond(h1, m1, s1);
		int endTime = toSecond(h2, m2, s2);

		// 12시간마다 초기화 되는 주기 운동
		// 3600초(60분) 동안 초침이 분침은 59번 만난다.

		int startTimeCount = calculate(startTime);
		int endTimeCount = calculate(endTime);

		int answer = endTimeCount - startTimeCount;
		// 현재 시각에 울리는지
		if (alarmNow(startTime)) {
			answer++;
		}

		return answer;
	}
}