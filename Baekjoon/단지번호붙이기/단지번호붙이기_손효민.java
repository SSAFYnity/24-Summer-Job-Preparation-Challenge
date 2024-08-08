import java.io.*;
import java.util.*;

public class Main {

	static int N, cnt;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String[] st = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st[j]);
			}
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int total = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					cnt = 0;
					dfs(i, j);
					pq.add(cnt);
					total++;
				}
			}
		}
		
		System.out.println(total);
		for (int i = pq.size()-1; i >= 0; i--) {
			System.out.println(pq.poll());
		}
	}

	private static void dfs(int x, int y) {
		cnt++;
		visited[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
			int nx = x+ dx[d];
			int ny = y+ dy[d];
			
			if(nx<0 || ny<0 || nx >=N || ny >= N)
				continue;
			if(visited[nx][ny] || map[nx][ny] == 0)
				continue;
			
			dfs(nx, ny);
		}
	}

}
