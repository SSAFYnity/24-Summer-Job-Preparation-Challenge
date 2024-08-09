import java.util.*;
import java.io.*;

public class Main {

    static int n, inf = Integer.MAX_VALUE;
    static Map<Integer, Set<Integer>> parent = new HashMap<>();
    static Map<Integer, Integer> son = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int o = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        boolean[] visit = new boolean[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            parent.putIfAbsent(p, new HashSet<>());
            parent.get(p).add(s);

            son.put(s, p);
        }

        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {o, 0});
        visit[o] = true;
        int answer = -1;

        while (!que.isEmpty()) {
            int[] nowPerson = que.poll();
            int now = nowPerson[0];
            int cnt = nowPerson[1];

            if (now == t) {
                answer = cnt;
                break;
            }

            for (int i : parent.getOrDefault(now, new HashSet<>())) {
                if (visit[i]) continue;
                que.add(new int[] {i, cnt+1});
                visit[i] = true;
            }
            if (son.containsKey(now)) {
                visit[son.get(now)] = true;
                que.add(new int[] {son.get(now), cnt+1});
                visit[son.get(now)] = true;
            }
        }

        System.out.println(answer);

    }
}
