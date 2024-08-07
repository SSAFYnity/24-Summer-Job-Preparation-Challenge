import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int node;
        int weight;

        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    static int n, d;
    static int[] result;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지름길의 갯수
        d = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        for (int i = 0; i<10001; i++) {
            graph.add(new ArrayList<>());
        }

        result = new int[d+1];
        for (int i = 0; i<result.length; i++) {
            result[i] = i;
        }

        for (int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (end<=d) {
                graph.get(start).add(new Node(end, cost));
            }

        }

        dijkstra(0);
        System.out.println(result[d]);
    }

    static void dijkstra(int start) {
        for (int i = start; i<d; i++) {
            if (result[i+1] > result[i] + 1) {
                result[i+1] = result[i] + 1;
            }
            for (Node node: graph.get(i)) {
                if (result[i] + node.weight < result[node.node]) {
                    result[node.node] = result[i] + node.weight;
                }
            }
        }
    }
}