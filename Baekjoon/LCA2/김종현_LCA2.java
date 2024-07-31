import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<Integer>[] adj;
    private static int maxDepth, parent[][], depth[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];

        depth = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());
            adj[n1].add(n2);
            adj[n2].add(n1);
        }

        maxDepth = (int) Math.floor(Math.log(N) / Math.log(2));
        parent = new int[N + 1][maxDepth+1];

        setTree(1, 0, 1);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(stk.nextToken());
            int n2 = Integer.parseInt(stk.nextToken());
            sb.append(LCA(n1,n2)).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static int LCA(int n1, int n2) {
        if (n1 == 1 || n2 == 1) return 1;

        int target = n1; // 평탄화가 반영될 노드 (더 깊은 노드)
        int compare = n2; // 비교 노드

        if (depth[n1] < depth[n2]) {
            target = n2;
            compare = n1;
        }

        // 평탄화
        if (depth[target] != depth[compare]) {
            for (int i = maxDepth; i >= 0; i--) {
                if (depth[parent[target][i]] >= depth[compare]) {
                    target = parent[target][i];
                }
            }
        }

        // 공통조상 찾기
        int ret = target;

        if (target != compare) {
            for (int i = maxDepth; i >= 0; i--) {
                if (parent[target][i] != parent[compare][i]) {
                    target = parent[target][i];
                    compare = parent[compare][i];
                }
                ret = parent[target][i];
            }
        }
        return ret;
    }

    private static void setTree(int node, int p, int d) {
        depth[node] = d;
        parent[node][0] = p;

        for (int i = 1; i <= maxDepth; i++) {
            parent[node][i] = parent[parent[node][i - 1]][i - 1];
        }

        for (int child : adj[node]) {
            if (child == p) continue;
            setTree(child, node, d + 1);
        }
    }
}
