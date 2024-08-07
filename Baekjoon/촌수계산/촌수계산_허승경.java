import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean [] visited;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];

        for(int i = 0; i < n+1; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.get(x).add(y);
            list.get(y).add(x);
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(p1);
        int cnt [] = new int[n+1];      // 촌수값 저장

        while(!que.isEmpty()){
            int p = que.poll();

            if(p == p2) break;

            for(int i = 0; i < list.get(p).size(); i++){
                int tmp = list.get(p).get(i);

                if(cnt[tmp] != 0) continue;     // 이미 확인
                cnt[tmp] = cnt[p] + 1;      // 현재 촌수는 이전 촌수 + 1
                que.add(tmp);

            }
        }

        if(cnt[p2] != 0) System.out.print(cnt[p2]);
        else System.out.print(-1);
    }
}
