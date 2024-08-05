import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LCA2_김현진 {

    static ArrayList<Integer>[] tree;
    static int[] level;
    static int row = 0;
    static int[][] parent;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<Integer>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            tree[s].add(e);
            tree[e].add(s);
        }

        while ((1 << row) < (N - 1)) row++; // 최대 가능 level 구하기
        //int temp = 1; while (temp <= N) { temp <<= 1; row++; }

        parent = new int[row + 1][N + 1];
        level = new int[N + 1];
        visited = new boolean[N + 1];
        DFS(1, 1);
        //BFS(1);

        for (int k = 1; k <= row; k++) {
            for (int n = 1; n <= N; n++)
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
        }


        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    static int lca(int a, int b) {
        if (level[a] > level[b]) { // b가 더 아래
            int temp = a;
            a = b;
            b = temp;
        }

        /** 큰 값부터 탐색 **/

        // 같은 level 로
        //for (int r = row; r >= 0; r--) { if (Math.pow(2, r) <= level[b] - level[a])  //if (level[a] <= level[parent[r][b]]) //b = parent[r][b]; }
        int dif, bit = row;
        while ((dif = level[b] - level[a]) != 0) {
            while ((1 << bit) > dif) bit--;
            b = parent[bit][b];
        }

        // 조상 빠르게 찾기
        for (int r = row; r >= 0 && a != b; r--) {
            if (parent[r][a] != parent[r][b]) { // 가장 처음 다른 조상을 찾을 때까지
                a = parent[r][a];
                b = parent[r][b];
            }
        }


        if (a != b) a = parent[0][a]; // 부모 == 가자 처음 공통 조상
        return a;
    }


    private static void BFS(int node) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(node);
        visited[node] = true;

        int lev = 1, nextgnr = 1, cnt = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            int now_l = level[now];
            
            for (int next : tree[now]) {
                if (visited[next]) continue;

                visited[next] = true;
                queue.add(next);

                parent[0][next] = now; // 부모와
                //level[next] = now_l + 1;
                level[next] = lev;     // 레벨 저장. int[]로 넣어줄 수도 있지만, Queue 의 특성 이용
            }

            cnt++;
            if (cnt == nextgnr) {
                cnt = 0;
                nextgnr = queue.size(); // 다음 세대
                lev++;
            }
        }

    }

    static void DFS(int p, int l) {
        visited[p] = true;
        level[p] = l;

        for (int child : tree[p]) {
            if (visited[child]) continue;

            parent[0][child] = p;
            DFS(child, l + 1);
        }

    }

}