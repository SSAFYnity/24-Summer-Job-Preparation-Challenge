import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine().trim()); // 전체 사람 수

		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		List<List<Integer>> lists = new ArrayList<>();
		int m = Integer.parseInt(br.readLine().trim()); // 관계 수

		for (int i = 0; i <= n; i++) {
			lists.add(new ArrayList<>());
		}

		// 관계 양방향 연결
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			lists.get(x).add(y);
			lists.get(y).add(x);
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(a);
		int[] distance = new int[n + 1];

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < lists.get(cur).size(); i++) {
				int next = lists.get(cur).get(i);
				if (distance[next] != 0) {
					continue;
				}
				q.offer(next);
				distance[next] = distance[cur] + 1;
			}
		}

		int result = (distance[b] != 0) ? distance[b] : -1;
		bw.write(result + "\n");

		bw.flush();
		bw.close();
		br.close();
