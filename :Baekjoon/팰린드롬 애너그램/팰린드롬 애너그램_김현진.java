import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬 애너그램_김현진
{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int len = Integer.parseInt(br.readLine().trim());
		String str = br.readLine().trim();

		if (str.length() != len) {
			System.out.println("No");
			return;
		}

		int[] cnt = new int[26];

		for (int i = 0; i < len; i++) {
			cnt[str.charAt(i) - 'a']++;
		}

		if (len % 2 == 1)
			cnt[str.charAt(len / 2) - 'a']--;

		for (int i = 0; i < 26; i++) {
			if (cnt[i] % 2 == 1) {
				System.out.println("No");
				return;
			}
		}

		System.out.println("Yes");
	}
}