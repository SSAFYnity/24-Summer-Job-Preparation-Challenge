import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] map;
    static boolean [][] visited;
    static int cnt;
    static int n;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];    // 방문 배열

        for(int i = 0; i < n; i++){
            String[] strArr = br.readLine().split("");
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j< n; j++){
                if(map[i][j] == 1 && !visited[i][j]){        // 1이면서 아직 방문 전
                    cnt = 0;
                    bfs(i, j);
                    pq.offer(cnt);
                }

            }
        }

        System.out.println(pq.size());
        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }

    static void bfs(int i, int j){
        Queue<Point> que = new LinkedList<>();
        visited[i][j] = true;
        que.add(new Point(i, j));

        int [] dx = {0, 1, 0, -1};
        int [] dy = {1, 0, -1, 0};

        while(!que.isEmpty()){
            Point p = que.poll();
            cnt++;

            for(int idx = 0; idx < 4; idx++){
                int tx = p.i + dx[idx];
                int ty = p.j + dy[idx];

                if(tx < 0 || tx >= n || ty < 0 || ty >= n) continue;

                if(map[tx][ty] == 1 && !visited[tx][ty]){
                    que.add(new Point(tx, ty));
                    visited[tx][ty] = true;
                }
            }
        }

    }

    static class Point{
        int i;
        int j;

        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
