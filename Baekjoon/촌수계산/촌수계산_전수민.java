import java.io.*;
import java.util.*;

public class Main {
    // 각각 정점 개수, 확인해야할 노두 2개, 간선 개수
    static int N, me, cousin, M;
    static boolean foundAns = false;
    static ArrayList<Integer> [] lists;
    static boolean [] isVisited;
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br   = new BufferedReader(new InputStreamReader(System.in));
        N       = Integer.parseInt(br.readLine());
        StringTokenizer st  = new StringTokenizer(br.readLine());
        me      = Integer.parseInt(st.nextToken());
        cousin  = Integer.parseInt(st.nextToken());
        M       = Integer.parseInt(br.readLine());

        lists = new ArrayList[N+1];
        isVisited = new boolean[N+1]; isVisited[me] = true;
        for (int i = 1; i <=N ; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start   = Integer.parseInt(st.nextToken());
            int end     = Integer.parseInt(st.nextToken());
            lists[start].add(end);
            lists[end].add(start);
        }
        dfs(0, me);
        if (!foundAns) {
            System.out.println(-1);
        }
    }

    public static void dfs(int depth, int now) {
        // 1. 기저 조건
        if(now == cousin) {
            System.out.println(depth);
            foundAns = true;
            return;
        }
        // 2. 계산 조건
        for (int i = 0; i < lists[now].size(); i++) {
            int next = lists[now].get(i);
            if(!isVisited[next]) {
                isVisited[next] = true;
                dfs(depth+1, next);
                if(foundAns) return;
                isVisited[next] = false;
            }
        }
    }
}