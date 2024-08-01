import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// [백준] 부분합
public class Main {

	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer stk = new StringTokenizer(br.readLine());
		 int N = Integer.parseInt(stk.nextToken());
		 int S = Integer.parseInt(stk.nextToken());
		 
		 // 입력과 동시에 누적합 계산
		 int[] arr = new int[N+1];
		 stk = new StringTokenizer(br.readLine());
		 for(int i=0; i<N; i++) {
			 arr[i+1] = arr[i] + Integer.parseInt(stk.nextToken());
		 }
		 
		 // 이분 탐색
		 for(int i=1; i<=N; i++) {			 
			 int start = i;
			 int end = N;
			 int mid = Integer.MAX_VALUE;
			 
			 while(start<=end) {
				 mid = (start + end) / 2;
				 int diff = arr[mid] - arr[i-1];
				 
				 if(diff >= S) {
					 int len = mid - i + 1;
					 min = (len < min) ? len : min;
					 end = mid - 1;
				 }else {
					 start = mid + 1;
				 }
			 }
		 }
		 
		 // 출력
		 if(min==Integer.MAX_VALUE) System.out.print(0);
		 else System.out.print(min);
	}
}
