package 누적합;
import java.io.*;
import java.util.*;


public class BOJ_1806_부분합 {

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int ans=Integer.MAX_VALUE;
	
		int arr [] = new int [N];
		
		st= new StringTokenizer(br.readLine());
		
		for(int i=0; i<N;i++) 
		{
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		int start =0;
		int end =0;
		int sum=0;
		
		
		while(end!=N) 
		{
			if(sum>=S) 
			{
				sum=sum-arr[start];
				ans = Math.min(ans, end-start);
				start++;
			}else
				sum=sum+arr[end++];
			
		}
		
		
		
		

		
		if(ans==Long.MAX_VALUE)
			ans = 0;
		
		System.out.println(ans);
		
	}

}
