import java.util.*;

class Solution {
	// 곡괭이로 광물을 캐면 피로도 소모
	// 각 곡괭이는 0~5개를 가지고 있으며, 광물 5개를 캐면 사용 불가
	// 한 번 사용 시작한 곡괭이는 사용할 수 없을 때까지 사용 (5번 연속 사용)
	// 광물은 주어진 순서대로 캘 수 있음
	// 최소한의 피로도로 모든 광물을 캐거나, 사용할 곡괭이가 없을 때까지 광물 캐기

	// 백트래킹

	private int answer;
	private Map<String, List<Integer>> digEnergy;

	private void dfs(int depth, int hp, int[] picks, String[] minerals) {
		// 가지치기: 피로도 최소값과 비교
		if (answer <= hp) {
			return;
		}

		// 모든 광물을 다 캤다면
		if (depth >= minerals.length || (picks[0] == 0 && picks[1] == 0 && picks[2] == 0)) {
			answer = Math.min(answer, hp);
			return;
		}

		// 광물 캐기
		for (int i = 0; i < 3; i++) {
			// 0: 다이아, 1: 철, 2: 돌
			if (picks[i] == 0) {
				continue;
			}
			int result = 0;
			for (int j = depth; j < Math.min(minerals.length, depth + 5); j++) {
				result += digEnergy.get(minerals[j]).get(i);
			}
			picks[i]--;
			dfs(depth + 5, hp + result, picks, minerals);
			picks[i]++;
		}
	}

	private void init() {
		answer = Integer.MAX_VALUE;
		digEnergy = new HashMap<>();
	}

	public int solution(int[] picks, String[] minerals) {
		// init
		init();
		digEnergy.put("diamond", Arrays.asList(1, 5, 25));
		digEnergy.put("iron", Arrays.asList(1, 1, 5));
		digEnergy.put("stone", Arrays.asList(1, 1, 1));

		// 백트랙킹
		dfs(0, 0, picks, minerals);

		return answer;
	}
}