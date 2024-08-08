import java.io.*;
import java.util.*;

public class Main {

    static int [][] arr;
    static int [] idx = new int[]{-1,0,1,0};
    static int [] idy = new int[]{0,1,0,-1};
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - 48;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 1){
                    cnt++;
                    list.add(bfs(new Coordinate(i,j)));
                }
            }
        }
        Collections.sort(list);
        System.out.println(cnt);
        for(int temp: list){
            System.out.println(temp);
        }

    }

    public static int bfs(Coordinate now) {
        // 공터: 0, 미방문 단지: 1, 방문한 단지: 2
        int apart_cnt = 1;
        ArrayDeque<Coordinate> aq1 = new ArrayDeque<>();

        aq1.add(now);
        arr[now.x][now.y] = 2;

        while(!aq1.isEmpty()) {
            Coordinate cur = aq1.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + idx[i];
                int ny = cur.y + idy[i];
                if(nx>=0 && ny>= 0 && nx< arr.length && ny < arr.length){
                    if(arr[nx][ny] == 1) {
                        arr[nx][ny] = 2;
                        aq1.add(new Coordinate(nx,ny));
                        apart_cnt++;
                    }
                }
            }
        }
        return apart_cnt;
    }
}
class Coordinate {
    int x;
    int y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }
}