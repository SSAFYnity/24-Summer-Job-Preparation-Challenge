package boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2075_N번_큰_수 {
    public static void main(String[] args) throws IOException {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(sc.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(sc.readLine());
            for(int j=0; j<N; j++){
                pq.add(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i=0; i<N-1; i++){
            pq.poll();
        }

        System.out.println(pq.peek());

    }
}