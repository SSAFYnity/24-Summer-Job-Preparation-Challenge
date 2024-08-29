import java.io.*;
import java.util.*;

public class 퇴사_김현진 {

	static int max = 0;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int Case = sc.nextInt();
		int[] T = new int[Case];
		int[] P = new int[Case];
		for(int i=0;i<Case;i++) {
			T[i]=sc.nextInt();	
			P[i]=sc.nextInt();	
		}
		
		int[] dp = new int[Case+1];
		
		for(int i=0;i<Case;i++) {
			if(i+T[i]<=Case) {
				dp[i+T[i]]=Math.max(dp[i+T[i]],dp[i]+P[i]);	
			} dp[i+1]=Math.max(dp[i+1],dp[i]);
		} System.out.println(dp[Case]);	
	}
}

