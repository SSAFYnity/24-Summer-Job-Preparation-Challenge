import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer> list[];
	static boolean visited[];
	static int ans = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int p1 = sc.nextInt();
		int p2 = sc.nextInt();
		int M = sc.nextInt(); 

		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();

			list[n1].add(n2);
			list[n2].add(n1);
		}

		BFS(p1, p2, 0);

		System.out.println(ans);
	}

	public static void BFS(int p1, int p2, int depth) {
		Queue<int[]> q = new LinkedList<>();
		visited[p1] = true;
		q.add(new int[] { p1, 0 });

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			if (curr[0] == p2) {
				ans = curr[1];
				break;
			}
			for (int l : list[curr[0]]) {
				if (!visited[l]) {
					visited[l] = true;
					q.add(new int[] { l, curr[1] + 1 });
				}
			}
		}
	}
}