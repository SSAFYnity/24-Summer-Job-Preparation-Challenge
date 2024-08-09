import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int a[] = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum=0;
		int left=0;
		int right=0;
		int ans = Integer.MAX_VALUE;
		int leng =0;
		while(right <= n) {
			if(sum >= s) {
				sum -= a[left++];
				leng = right - left + 1;
				if(ans > leng) ans = leng;
			}else if(sum < s) {
				sum += a[right++];
			}
		}
		
		System.out.println((ans) == Integer.MAX_VALUE ? 0 : ans);

	}

}