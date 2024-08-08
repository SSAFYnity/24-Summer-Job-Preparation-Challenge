package S1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1446_지름길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Load>[] adjList = new ArrayList[D + 1];
        for (int i = 0; i <= D; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            if (end <= D && end - start > length) { // 목적지를 지나치지 않고 && 1칸씩 가는 것 보다 이득일 때만 저장
                adjList[start].add(new Load(end, length));
            }
        }

        Queue<Load> pq = new ArrayDeque<>();
        int[] mins = new int[D + 1];
        Arrays.fill(mins, Integer.MAX_VALUE);

        pq.offer(new Load(0, 0));

        while (!pq.isEmpty()) {
            Load load = pq.poll();
            if (mins[load.idx] < load.length) {
                continue;
            }
            for (Load adj : adjList[load.idx]) {
                if (mins[adj.idx] > load.length + adj.length) {
                    mins[adj.idx] = load.length + adj.length;
                    pq.offer(new Load(adj.idx, load.length + adj.length));
                }
            }
            if (load.idx + 1 <= D && mins[load.idx + 1] > load.length + 1) {
                mins[load.idx + 1] = load.length + 1;
                pq.offer(new Load(load.idx + 1, load.length + 1));
            }
        }

        bw.write(Integer.toString(mins[D]));
        bw.flush();
    }

    private static class Load {
        int idx;
        int length;

        public Load(int idx, int length) {
            this.idx = idx;
            this.length = length;
        }
    }
}
