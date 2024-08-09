package my;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/*1. 큐에 넣을때마다 house++
2. house를 리스트에 넣어줌
3. bfs 돌때마다 cnt++ */

public class BJ_2667_단지번호붙이기 {
	static int N;
	static int house;
	static boolean[][] visited;
	static int[][] arr;
	static Queue<int[]>q;
	static List<Integer>list;
	
	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		
		visited=new boolean[N][N];
		arr=new int[N][N];
		q=new ArrayDeque<>();
		list=new ArrayList<>();
		
		int cnt=0;
		
		for(int i=0; i<N; i++) {
			String str=br.readLine();
			for(int j=0; j<N; j++) {
				char input=str.charAt(j);
				arr[i][j]=input-'0';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j]==1 && !visited[i][j]) {
					cnt++;
					visited[i][j]=true;
					q.add(new int[] {i,j});
					bfs();
				}
			}
		}
		
		Collections.sort(list);
		
		System.out.println(cnt);
		for(int ele : list) {
			System.out.println(ele);
		}
	}

	private static void bfs() {
	
		int house=1;
		
		while(!q.isEmpty()) {
			int[] qArr=q.poll();
			int x=qArr[0];
			int y=qArr[1];
			
			for(int i=0; i<4; i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				if(isRange(nx, ny) && arr[nx][ny]==1 &&!visited[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new int[] {nx,ny});
					house++;
				}
			}
		}
		list.add(house);
		
	}

	private static boolean isRange(int nx, int ny) {
		if(nx>=0 && nx<N && ny>=0 && ny<N) return true;
		return false;
	}
}
