import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 횡단보도_김현진 {
	static int N, M;
	static long[] dist;
	static Long INF = Long.MAX_VALUE;

	static class Node implements Comparable<Node> {
		int v;
		long time;

		Node(int v, long time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return (int) (time - o.time);
		}
	}

	static ArrayList<Node>[] list;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		dist = new long[N + 1];
		Arrays.fill(dist, INF);
		list = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			int st = sc.nextInt();
			int ed = sc.nextInt();
			list[st].add(new Node(ed, i));
			list[ed].add(new Node(st, i));
		}
		dijkstra(1);
		System.out.println(dist[N]);
	}

	static void dijkstra(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		dist[start] = 0;
		queue.add(new Node(start, 0));

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (dist[current.v] < current.time)
				continue;

			for (Node next : list[current.v]) {
				long order = current.time % M;
				order = next.time - order;
				if (order < 0)
					order += M;
				long nextTime = order + current.time;

				if (dist[next.v] > nextTime) {
					dist[next.v] = nextTime;
					queue.add(new Node(next.v, nextTime));
				}
			}
		}
	}
}