import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] parent;
    static int[] level;
    static ArrayList<Integer>[] list;
    static boolean[] visit;
    static int POW_LIMIT = 21;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        parent = new int[N + 1][POW_LIMIT];
        level = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }
        setParent();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(LCA(a, b)).append("\n");
        }
        System.out.println(sb);
    }

    private static void setParent() {
        setLevel(1, 0);
        for (int i = 1; i < POW_LIMIT; i++) {
            for (int j = 1; j <= N; j++) {
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }
    }

    private static void setLevel(int x, int depth) {
        visit[x] = true;
        level[x] = depth;
        for (int node : list[x]) {
            if (visit[node]) continue;
            parent[node][0] = x;
            setLevel(node, depth + 1);
        }
    }

    static int LCA(int a, int b) {
        if (level[a] > level[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        for (int i = POW_LIMIT - 1; i >= 0; i--) {
            if (level[b] - level[a] >= (1 << i)) {
                b = parent[b][i];
            }
        }
        if (a == b) return a;
        for (int i = POW_LIMIT - 1; i >= 0; i--) {
            if (parent[a][i] != parent[b][i]) {
                a = parent[a][i];
                b = parent[b][i];
            }
        }
        return parent[a][0];
    }
}