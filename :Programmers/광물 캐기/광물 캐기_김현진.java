import java.util.*;

class Solution {
	private static class State {
		public final int fatigue; // 현재 피로도
		public final int index; // 현재 광물 인덱스
		public final int[] picks; // 남은 곡괭이 개수

		public State(int index, int fatigue, int[] picks) {
			this.fatigue = fatigue;
			this.index = index;
			this.picks = Arrays.copyOf(picks, picks.length); // picks 배열을 복사하여 상태를 저장
		}
	}

	public int solution(int[] picks, String[] minerals) {
		int answer = Integer.MAX_VALUE; // 최소 피로도를 저장하기 위한 변수

		// 각 곡괭이로 각 광물을 캘 때의 피로도 테이블
		int[][] fatigueTable = {
				{ 1, 1, 1 }, // 다이아몬드 곡괭이: 다이아몬드, 철, 돌
				{ 5, 1, 1 }, // 철 곡괭이: 다이아몬드, 철, 돌
				{ 25, 5, 1 } // 돌 곡괭이: 다이아몬드, 철, 돌
		};

		// 각 광물을 인덱스로 매핑 (다이아몬드: 0, 철: 1, 돌: 2)
		Map<String, Integer> mineralMap = Map.of("diamond", 0, "iron", 1, "stone", 2);

		Stack<State> stk = new Stack<>();
		stk.push(new State(0, 0, picks)); // 초기 상태를 스택에 푸시

		while (!stk.isEmpty()) {
			State state = stk.pop();
			int index = state.index;
			int fatigue = state.fatigue;

			// 모든 광물을 캔 경우, 현재 피로도를 최소 피로도와 비교하여 업데이트
			if (index >= minerals.length) {
				answer = Math.min(answer, fatigue);
				continue;
			}

			boolean noMorePicks = true; // 더 이상 사용할 곡괭이가 없는지 확인하는 플래그

			for (int i = 0; i < 3; i++) {
				if (state.picks[i] > 0) { // 사용할 수 있는 곡괭이가 있을 경우
					noMorePicks = false;
					int[] newPicks = Arrays.copyOf(state.picks, 3); // 곡괭이 배열 복사
					newPicks[i]--; // 사용한 곡괭이 감소
					int newFatigue = fatigue; // 새로운 피로도 계산
					int newIndex = index;

					// 5개의 광물을 캐면서 피로도를 누적
					for (int j = 0; j < 5 && newIndex < minerals.length; j++, newIndex++) {
						int mineralType = mineralMap.get(minerals[newIndex]);
						newFatigue += fatigueTable[i][mineralType];
					}

					// 새로운 상태를 스택에 푸시
					stk.push(new State(newIndex, newFatigue, newPicks));
				}
			}

			// 모든 곡괭이를 사용한 경우, 현재 피로도를 최소 피로도와 비교하여 업데이트
			if (noMorePicks) {
				answer = Math.min(answer, fatigue);
			}
		}

		return answer; // 최소 피로도 반환
	}
}