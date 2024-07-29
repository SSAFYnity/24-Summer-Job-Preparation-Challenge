import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int size = s.length();
		int aCount = 0;
		for(int i=0;i<size;i++) {
			if(s.charAt(i)=='a') { aCount++; }
		}
		if(aCount <= 1 || aCount >= size-1) {
			System.out.println(0);
			return;
		}
		int start = size-aCount;
		int count = 0;
		for(int i=start;i<size;i++) {
			if(s.charAt(i)=='a') { count++; }
		}
		int maxCount = count;
		for(int i=0;i<size;i++) {
			if(s.charAt(i)=='a') {
				if(s.charAt(start)=='b') {
					count++;
				}
				maxCount = (maxCount>=count) ? maxCount : count;
				if(maxCount==aCount) {break;}
			}
			else {
				if(s.charAt(start)=='a') {
					count--;
				}
			}
			start = (start+1)%size;
		}
		System.out.println(aCount-maxCount);
	}
}