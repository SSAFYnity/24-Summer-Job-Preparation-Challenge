package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BJ_2644_ÃÌ¼ö°è»ê {
	
	static int N, M, first, second;
	static int answer;
	static List<Integer>[] list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int x=0, y=0;
		
		N=Integer.parseInt(br.readLine());
		list=new ArrayList[N+1];
		visited=new boolean[N+1];
		
		answer=-1;
		
		for(int i=0; i<N+1; i++) {
			list[i]=new ArrayList<Integer>();
		}
		
		st=new StringTokenizer(br.readLine());
		
		first=Integer.parseInt(st.nextToken());
		second=Integer.parseInt(st.nextToken());
		
		M=Integer.parseInt(br.readLine());
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			x=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		dfs(first, 0);
		
		System.out.println(answer);
	}

	private static void dfs(int x, int cnt) {

		int size=list[x].size();
		visited[x]=true;
		
		for(int i=0; i<size; i++) {
			int a=list[x].get(i);
			if(!visited[a]) {
				
//				System.out.println("°ÅÃÆÀ½ : "+a);
				
				if(a==second) {
					answer=cnt+1;
					return;
				}
				dfs(a, cnt+1);
			}
		}
		
	}
}
