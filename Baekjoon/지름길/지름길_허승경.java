package August_2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point{
    int start;
    int end;
    int dis;

    public Point(int start, int end, int dis){
        this.start = start;
        this.end = end;
        this.dis = dis;
    }
}

public class BOJ_1446_0805_dp {
    static int N;
    static int D;
    static List<Point> map = new ArrayList<>();
    static int [] arr;          // 각 위치별 최소 거리
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            if(start > D || end > D ) continue;         // 지름길 사용 X

            map.add(new Point(start, end, dis));
        }
        arr = new int[D+1];     // 거리마다 최소값
        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }

        findDistance();

        System.out.println(arr[D]);
    }

    static void findDistance(){
        for(int i = 1; i <= D; i++){    // 1부터 D까지 돌리기
            for(int j = 0; j < map.size(); j++){
                Point p = map.get(j);

                if(i == p.end){         // 지름길 있는 경우
                    int tmp = arr[p.start] + p.dis;     // 시작 위치까지의 거리 + 지름길 거리
                    arr[i] = Math.min(arr[i], Math.min(arr[i-1]+1, tmp));
                }else{
                    arr[i] = Math.min(arr[i], arr[i-1]+1);
                }
            }
        }
    }
}
