
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 횡단보도 {
	// boj Gold1
	
	static int N,M;
	static Long INF = Long.MAX_VALUE;
	static long[] dsit;
	static class Node implements Comparable<Node> {
		int goal;
		long time;
		Node(int goal, long time) {
			this.goal = goal;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return (int)(time - o.time);
		}
	}
	static ArrayList<Node>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dsit = new long[N+1];
		Arrays.fill(dsit, INF);
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end, i));
			list[end].add(new Node(start, i));
		}
		
		dijk(1);
		System.out.println(dsit[N]);
	}

	private static void dijk(int start) {
		dsit[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			
			if (dsit[temp.goal] < temp.time) {
				continue;
			}
			
			for (Node next : list[temp.goal]) {
				long cycle = temp.time % M;
				cycle = next.time - cycle;
				if (cycle < 0) {
					cycle += M;
				}
				long nextTime = cycle + temp.time;
				
				if (dsit[next.goal] > nextTime) {
					dsit[next.goal] = nextTime;
					pq.add(new Node(next.goal, nextTime));
				}
			}
		}
	}
}
