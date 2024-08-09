import java.util.*;
import java.io.*;

class Main {

    static class Node {

        int end;
        int cost;

        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static final int MAX_LENGTH = 10000;
    static int N, D;
    static List<Node>[] graph;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        graph = new ArrayList[MAX_LENGTH + 1];
        for (int i = 0; i <= MAX_LENGTH; i++) {
            graph[i]= new ArrayList<>();
        }
        distance = new int[MAX_LENGTH + 1];
        for (int i = 0; i < distance.length; i++) {
            distance[i] = i;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (end > D || end - start > cost) {
                graph[start].add(new Node(end, cost));
            }
        }

        dp();

        System.out.println(distance[D]);
    }

    static void dp() {
        for (int i = 0; i <= D; i++) {
            if (i + 1 <= MAX_LENGTH && distance[i + 1] > distance[i] + 1) {
                distance[i + 1] = distance[i] + 1;
            }
            for (Node next : graph[i]) {
                if (distance[next.end] > distance[i] + next.cost) {
                    distance[next.end] = distance[i] + next.cost;
                }
            }
        }
    }
}