import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Sign>[] adjList = new ArrayList[N + 1]; // 경로+시간
        long[] result = new long[N + 1]; // 최단 시간

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            result[i] = Long.MAX_VALUE;
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            adjList[A].add(new Sign(B, i));
            adjList[B].add(new Sign(A, i));
        }

        // Dijkstra
        Queue<Road> q = new PriorityQueue<>();
        q.add(new Road(1, 0L));
        result[1] = 0;

        while (!q.isEmpty()) {
            Road road = q.poll();
            if (road.time > result[road.site]) { // 이미 더 빠른 경우 처리함
                continue;
            }
            if (road.site == N) { // N 찾음
                break;
            }

            // 최단시간 업데이트
            for (Sign sign : adjList[road.site]) {

                int temp = (int) (road.time % M); // 현재 시간의 주기 위치

                if (temp <= sign.minT) { // 이번 주기에 이동
                    temp = sign.minT - temp; // 추가되는 시간
                } else { // 다음 주기에 이동
                    temp = sign.minT + M - temp; // 추가되는 시간
                }

                if (result[sign.site] > road.time + temp) { // 더 짧은 경우 찾음
                    result[sign.site] = road.time + temp; // 업데이트
                    q.add(new Road(sign.site, road.time + temp)); // 큐에도 넣기
                }
            }
        }

        // Output
        bw.write(Long.toString(result[N]));
        bw.flush();
    }

    private static class Sign {
        int site;
        int minT;

        public Sign(int site, int minT) {
            this.site = site;
            this.minT = minT;
        }
    }

    private static class Road implements Comparable<Road> {
        int site;
        long time;

        public Road(int site, long time) {
            this.site = site;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return Long.compare(this.time, o.time);
        }
    }
}