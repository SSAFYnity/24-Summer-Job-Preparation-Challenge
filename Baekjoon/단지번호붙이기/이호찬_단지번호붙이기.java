import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

	private static int N;
	private static final int[] dRow = {-1, 1, 0, 0}, dCol = {0, 0, -1, 1};
	private static int[][] board;
	private static boolean[][] visit;

	private static int bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visit[sr][sc] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int k = 0; k < 4; k++) {
				int r = cur[0] + dRow[k];
				int c = cur[1] + dCol[k];
				// 범위 확인
				if (r < 0 || c < 0 || r >= N || c >= N) {
					continue;
				}
				// 단지 & 방문 확인
				if (board[r][c] == 0 || visit[r][c]) {
					continue;
				}
				q.offer(new int[] {r, c});
				visit[r][c] = true;
				cnt++;
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().trim());
		board = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}

		List<Integer> counts = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] || board[i][j] == 0) {
					continue;
				}
				counts.add(bfs(i, j));
			}
		}

		Collections.sort(counts);

		bw.write(counts.size() + "\n");
		for (int cnt : counts) {
			bw.write(cnt + "\n");
		}

		bw.flush();
		bw.close();
		br.close();
	}
}