import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int len = Integer.parseInt(br.readLine());
		String str = br.readLine();

		String answer = "Yes";

		Map<Character, Integer> map = new HashMap<>();

		/*
		가운데 문자빼고 나머지 짝수면 Yes
		아니면 No
		문자 개수가 짝수면 다 세고, 홀수면 가운데 문자만 제외
		 */
		if (len % 2 == 0) {
			for (char c : str.toCharArray()) {
				map.put(c, map.getOrDefault(c, 0) + 1);
			}
		} else {
			for (int i = 0; i < len; i++) {
				if (len/2==i) continue;
				char c = str.charAt(i);
				map.put(c, map.getOrDefault(c, 0) + 1);
			}
		}

		for (Integer val : map.values()) {
			if (val%2 != 0) {
				answer = "No";
				break;
			}
		}
		System.out.println(answer);
	}

}
