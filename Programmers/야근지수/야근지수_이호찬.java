import java.util.*;

class Solution {

	// 야근 피로도: 남은 일의 작업량을 제곱 합산

	// works[] 내림차순 정렬 (우선순위 큐)
	// n만큼 각 원소의 최댓값에서 1씩 차감

	public long solution(int n, int[] works) {
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);

		for (int work : works) {
			pq.offer(work);
		}

		while (n-- > 0 && pq.peek() != 0) {
			int cur = pq.poll();
			pq.offer(cur - 1);
		}

		long answer = 0;
		while (!pq.isEmpty()) {
			answer += Math.pow(pq.poll(), 2);
		}

		return answer;
	}
}