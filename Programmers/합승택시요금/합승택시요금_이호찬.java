import java.util.*;

class Solution {

	// 출발: S, 도착: A, B
	// S에서 합승하여 출발할 시 택시 요금은 한 번만 부과
	// A,B에 도달하는데 소요되는 택시 요금 최소값 구하기

	private int answer;
	private final int INF = 10_000_000;
	private List<List<Road>> roads;

	private int[] dijkstra(int size, int start) {
		int[] distance = new int[size + 1];
		Arrays.fill(distance, INF);
		distance[start] = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(start);

		while (!pq.isEmpty()) {
			int cur = pq.poll();
			for (int i = 0; i < roads.get(cur).size(); i++) {
				int cost = distance[cur] + roads.get(cur).get(i).cost;
				if (cost < distance[roads.get(cur).get(i).to]) {
					distance[roads.get(cur).get(i).to] = cost;
					pq.offer(roads.get(cur).get(i).to);
				}
			}
		}
		return distance;
	}

	private void print(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		roads = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			roads.add(new ArrayList<>());
		}

		// 도로 정보 추가
		for (int i = 0; i < fares.length; i++) {
			// 양방향 연결
			roads.get(fares[i][0]).add(new Road(fares[i][0], fares[i][1], fares[i][2]));
			roads.get(fares[i][1]).add(new Road(fares[i][1], fares[i][0], fares[i][2]));
		}

		answer = INF;

		// 합승 구간 비용: s지점에서 모든 지점까지 최단 거리 계산
		int[] pairDistance = dijkstra(n, s);

		// 개별 구간 비용: 각 지점에서 A, B까지 거리의 합
		int[] aDistance = dijkstra(n, a);
		int[] bDistance = dijkstra(n, b);

		for (int i = 1; i <= n; i++) {
			int result = pairDistance[i] + aDistance[i] + bDistance[i];
			answer = Math.min(answer, result);
		}

		return answer;
	}

	private class Road implements Comparable<Road> {
		int from;
		int to;
		int cost;

		public Road(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		public int compareTo(Road r) {
			return this.cost - r.cost;
		}
	}
}