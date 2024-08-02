import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	// 연속된 수들의 부분합 중에서 그 합이 S 이상이 되는 것 중, 가장 짧은 길이 구하기
	// 만족하는 부분합이 없다면, 0출력

	// 투 포인터를 이용한 누적합 문제

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 10 <= N < 100,000
		int S = Integer.parseInt(st.nextToken()); // 0 < S <= 100,000,000

		int[] nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 투 포인터
		int minLen = 1_000_000;
		int left = 0;
		int sum = 0;

		for (int right = 0; right < N; right++) {
			sum += nums[right];

			while (left <= right && S <= sum - nums[left]) {
				sum -= nums[left++];
			}

			// 길이 최소값 갱신
			if (S <= sum) {
				minLen = Math.min(minLen, (right - left + 1));
			}
		}

		minLen = (minLen == 1_000_000) ? 0 : minLen; // 만족하는 부분합이 없다면 0출력
		bw.write(minLen + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}