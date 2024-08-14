import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 2667 단지번호붙이기
public class 단지번호붙이기_김현진 {
	static int N; // 행열의 크기
	static int[][] array; // 맵
	static boolean[][] visited; // 방문배열

	// 사방탐색 하기 위해
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 사방탐색하면서 업데이트되는 현재 좌표
	static int currX;
	static int currY;

	static int cnt; // 단지안의 사람들
	static ArrayList<Integer> list = new ArrayList<>(); // 출력을 위해

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(sc.readLine());
		array = new int[N][N];
		visited = new boolean[N][N];

		// array에 입력값 넣기
		for (int i = 0; i < N; i++) {
			String str = sc.readLine();
			for (int j = 0; j < N; j++) {
				int num = str.charAt(j) - '0';
				array[i][j] = num;
			}
		}

		/*
		 * // check
		 * for(int i=0; i<N; i++) {
		 * for(int j=0; j<N; j++) {
		 * System.out.print(array[i][j]);
		 * }
		 * System.out.println();
		 * }
		 */

		// find start point
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (array[i][j] == 1 && !visited[i][j]) { // 연결되어 있고 방문하지 않았다면
					cnt = 1; // 단지수 초기화
					DFS(i, j); // 현재 맵의 좌표(행,열) 보냄
					list.add(cnt); // 단지수 넣기
				}
			}
		}

		Collections.sort(list); // 오름차순으로 출력하기 위해
		System.out.println(list.size()); // 단지 수
		for (int i : list) {
			System.out.println(i);
		}
	}

	private static void DFS(int x, int y) {
		visited[x][y] = true; // 방문처리
		for (int i = 0; i < 4; i++) { // 사방탐색 하기
			currX = dx[i] + x;
			currY = dy[i] + y;

			// 범위체크
			// 사방탐색하면서 업데이트한 x,y의 현재 좌표가 배열의 범위를 벗어나지 않고
			// 배열이 연결되어있으면서 방문하지 않았을 경우
			if (currX >= 0 && currX < N && currY >= 0 && currY < N
					&& array[currX][currY] == 1 && !visited[currX][currY]) {
				cnt++; // 사람들 카운트
				DFS(currX, currY); // 현재 좌표로 재귀
			}
		}

	}

}