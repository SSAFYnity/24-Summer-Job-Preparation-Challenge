import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 문자열교환 {
	// boj Silver1
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int a_length = 0;
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				a_length++;
			}
		}
		
		for (int i = 0; i < str.length(); i++) {
			int b = 0;
			for (int j = i; j < i+a_length; j++) {
				if (str.charAt(j % str.length()) == 'b') {
					b++;
				}
			}
			
			result = b < result ? b : result;
		}
		
		System.out.println(result);
	}
}
