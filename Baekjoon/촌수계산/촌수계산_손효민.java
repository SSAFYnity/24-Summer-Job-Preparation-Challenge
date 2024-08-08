import java.io.*;
import java.util.*;

public class Main {

	static int N, M, sx, sy;
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		list = new ArrayList[N+1];
		for(int i=0;i<=N;i++) list[i] = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list[x].add(y);
			list[y].add(x);
		}
		
		int ans = bfs();
		System.out.println(ans);
	}
	
	public static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		q.add(new int[] {sx,0});
		visit[sx] = true;
		
		while(!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int cnt = arr[1];
			
			if(x==sy) return cnt;
			
			for(int num : list[x]) {
				if(visit[num]) continue;
				
				q.add(new int[] {num, cnt+1});
				visit[num] = true;
			}
		}
		return -1;		
	}
}
