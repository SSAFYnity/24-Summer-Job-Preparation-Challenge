package 문자열;

import java.util.*;
import java.io.*;

public class BOJ_1522_문자열교환 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		int min = Integer.MAX_VALUE;
		int length =input.length();
		int aCnt =0;
		int bCnt =0;
		
		for(int i=0; i<length;i++) 
		{
			if(input.charAt(i)=='a')
				aCnt++;
		}
		
		for(int i=0; i<length; i++) 
		{
			for(int j=i; j<aCnt+i; j++) 
			{
				if(input.charAt(j%length)=='b') 
					bCnt++;
			}
			min= Math.min(min, bCnt);
			bCnt=0;
		}
		

		System.out.println(min);


	}

}
