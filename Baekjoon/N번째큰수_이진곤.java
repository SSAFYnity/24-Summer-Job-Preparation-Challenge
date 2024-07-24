package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class N번째큰수_이진곤 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(in.readLine());
        int[][] table = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < N; i++) {
            pq.offer(new int[] { table[N - 1][i], N - 1, i }); // 숫자, 행, 열
        }

        int cnt = 0;
        int target = -1;
        while (cnt < N) {
            int[] info = pq.poll();
            target = info[0];
            if (info[1] > 0) {
                int newY = info[1] - 1;
                pq.offer(new int[] { table[newY][info[2]], newY, info[2] });
            }
            cnt++;
        }
        System.out.println(target);
    }
}