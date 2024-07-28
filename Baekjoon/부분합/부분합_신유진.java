package Baekjoon.부분합;
import java.io.*;
import java.util.StringTokenizer;

public class 부분합_신유진 {
    public static int N, S;
    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int startIdx = 0;
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            while (sum >= S) {
                minDis = Math.min(minDis, i - startIdx + 1);
                sum -= arr[startIdx++];
            }
        }

        if (minDis == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minDis);
        }
    }
}
