import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] data = new int[N];
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int answer = N+1;
		int count = 0;
		int startIndex = 0;
		for(int i=0;i<N;i++) {
			data[i] = Integer.parseInt(st.nextToken());
			sum+=data[i];
			if(count==answer) {
				sum-=data[startIndex++];
			}
			else {
				count++;
			}
			if(sum>=S) {
				while(sum-data[startIndex]>=S) {
					sum-=data[startIndex++];
					count--;
				}
				answer = count;
			}
		}
		if(answer > N) {answer = 0;}
		System.out.println(answer);
	}
}