import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// NxN 보드에서 N번째 큰 수 찾기
	// 모든 수는 자신의 한 칸 위에 있는 수보다 크다.

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine().trim());

		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				nums.add(Integer.parseInt(st.nextToken()));
			}
		}

		Collections.sort(nums, Collections.reverseOrder());

		int result = nums.get(N - 1);
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
