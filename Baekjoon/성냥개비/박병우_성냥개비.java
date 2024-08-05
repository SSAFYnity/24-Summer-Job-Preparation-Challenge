import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 성냥개비 {
	// boj Gold2
	static String[] dp = new String [101];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		dp[2] = "1";
		dp[3] = "7";
		dp[4] = "4";
		dp[5] = "2";
		dp[6] = "6";
		dp[7] = "8";
		
		String[] min = {null,null,"1","7","4","2","0","8"};
		for (int i = 8; i < 101; i++) {
			for (int j = 2; j < 8; j++) {
				if (i-j < 2) {
					continue;
				}
				String temp = dp[i-j] + min[j];
				
				if (dp[i] == null) {
					dp[i] = temp;
				} else {
					dp[i] = String.valueOf(Math.min(Long.parseLong(dp[i]), Long.parseLong(temp)));
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			String big = "";
			String small = "";
			
			// 작은수 계산
			small = dp[N];
			
			// 큰수 계산
			if (N % 2 == 1) {
				big += 7;
				for (int i = 0; i < (N / 2) - 1; i++) {
					big += 1;
				}
			} else {
				for (int i = 0; i < N/2; i++) {
					big += 1;
				}
			}
			
			// 결과출력
			System.out.println(small + " " + big);
		}
	}
}
