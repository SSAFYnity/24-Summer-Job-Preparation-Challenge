import java.io.*;
import java.util.List;
import java.util.ArrayList;

class 촌수계산_강민정 {
    static List<Integer>[] graph;       // 리스트 배열
    static boolean[] visited;
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());     // 1 <= 전체 사람의 수 <= 100
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        String[] people = br.readLine().split(" ");    // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        int people1 = Integer.parseInt(people[0]);      // 첫 번째 사람
        int people2 = Integer.parseInt(people[1]);      // 두 번째 사람
        int m = Integer.parseInt(br.readLine());        // 부모 자식들 간의 관계의 개수

        // 각 요소를 개별적으로 초기화
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 부모 자식들 간의 관계의 개수만큼 입력받기
        for(int i=0; i<m; i++) {
            String[] relation = br.readLine().split(" ");
            int parent = Integer.parseInt(relation[0]);
            int child = Integer.parseInt(relation[1]);

            // 양방향 연결
            graph[parent].add(child);
            graph[child].add(parent);
        }

        dfs(people1, people2, 0);

        System.out.println(answer);     // 두 사람의 촌수를 출력(계산할 수 없으면 -1)
    }

    /*
        start에서 end까지 가는 거리를 구하면 촌수가 됨
     */
    public static void dfs(int start, int end, int distance) {
        if(start == end) {      // 촌수를 계산할 두 번호가 만남
            answer = distance;
            return;
        }

        visited[start] = true;      // 방문 처리

        for(int num : graph[start]) {       // start와 연결된 번호들
            if(visited[num]) {      // 방문한 적 있는 번호는 건너띄기
                continue;
            }
            dfs(num, end, distance + 1);
        }
    }
}