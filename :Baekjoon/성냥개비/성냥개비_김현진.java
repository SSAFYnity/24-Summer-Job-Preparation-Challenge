import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int N;
	static long[] minDp;

	public static void main(String[] args) throws IOException {
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(sc.readLine());

		while (testCase-- > 0) {

			N = Integer.parseInt(sc.readLine());

			minDp = new long[101];

			Arrays.fill(minDp, Long.MAX_VALUE);
			minDp[2] = 1;
			minDp[3] = 7;
			minDp[4] = 4;
			minDp[5] = 2;
			minDp[6] = 6;
			minDp[7] = 8;
			minDp[8] = 10;

			String[] add = { "1", "7", "4", "2", "0", "8" };

			for (int i = 9; i <= 100; i++) {
				// 9부터 (7 , 0) (6,1)
				for (int j = 2; j <= 7; j++) {
					String line = "" + minDp[i - j] + add[j - 2];
					minDp[i] = Math.min(Long.parseLong(line), minDp[i]);
				}
			}

			long a = N / 2;
			long b = N % 2;

			if (b == 1)
				sb.append("7");
			else
				sb.append("1");

			for (int i = 1; i < a; i++) {
				sb.append("1");
			}
			System.out.println(minDp[N] + " " + sb);
		}
	}
}